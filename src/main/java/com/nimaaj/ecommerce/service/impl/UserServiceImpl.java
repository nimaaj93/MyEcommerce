package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Authentication;
import com.nimaaj.ecommerce.domain.Authority;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProfileDto;
import com.nimaaj.ecommerce.dto.UserDto;
import com.nimaaj.ecommerce.enumaration.UserRole;
import com.nimaaj.ecommerce.enumaration.UserType;
import com.nimaaj.ecommerce.exception.*;
import com.nimaaj.ecommerce.mapper.UserMapper;
import com.nimaaj.ecommerce.model.input.OtpVerification;
import com.nimaaj.ecommerce.model.input.UpdatePasswordModel;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;
import com.nimaaj.ecommerce.repository.AuthenticationRepository;
import com.nimaaj.ecommerce.repository.AuthorityRepository;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.security.AuthenticationHelper;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.OtpService;
import com.nimaaj.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationHelper authenticationHelper;
    private final UserMapper userMapper;
    private final OtpService otpService;

    @Override
    public ProfileDto getProfile() {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);
        return userRepository.findById(userId)
                .map(userMapper::toProfileDto)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    @Transactional
    public UserDto register(UserRegistrationModel model) {

        log.info("register() called for {}", model);

        Optional<User> optionalUser = userRepository.findOneWithAuthoritiesByMobileNumber(model.getMobileNumber());
        optionalUser.filter(User::isActivated).ifPresent(existingUser -> {
            throw new UserExistsException();
        });

        User user = optionalUser.orElse(new User());
        Authentication authentication =
                Optional.ofNullable(user.getAuthentication()).orElse(new Authentication());

        Optional<Authority> optionalUserAuth = user.getAuthorities().stream()
                .filter(authority -> authority.getAuthorityVal().equals(UserRole.USER.name()))
                .findAny();
        if (optionalUserAuth.isPresent()) {
            addUserAuthority(user);
        }

        user = saveUser(model, user, authentication);
        otpService.generateAndSendOtpForUser(user);
        return userMapper.toDto(user);
    }

    private User saveUser(UserRegistrationModel model, User user, Authentication authentication) {
        String userSalt = authenticationHelper.generateSalt();
        String hashedPass = authenticationHelper.hashPassword(model.getPassword(), userSalt);
        authentication.setSalt(userSalt);
        authentication.setPassword(hashedPass);
        authentication.setUser(user);
        user.setAuthentication(authentication);
        user.setUserType(UserType.PUBLIC);
        user.setActivated(false);
        user.setGender(model.getGender());
        user.setMobileNumber(model.getMobileNumber());
        user.setEmail(model.getEmail());
        user.setDateOfBirth(new Date(model.getDateOfBirth()));
        user = userRepository.save(user);
        authenticationRepository.save(authentication);
        return user;
    }

    private void addUserAuthority(User user) {
        Set<Authority> authorities = new HashSet<>();

        Authority userAuthority = authorityRepository.findByAuthorityVal(UserRole.USER.name());

        if (userAuthority == null) throw new AuthorityNotFoundException();

        authorities.add(userAuthority);
        user.setAuthorities(authorities);
    }

    @Override
    public UserDto activateUser(OtpVerification otpVerification) {
        log.info("activateUser() called for {}", otpVerification);
        User user = userRepository.findOneWithAuthoritiesByMobileNumber(otpVerification.getMobileNumber())
                .orElseThrow(UserNotFoundException::new);
        otpService.verifyOtpForUser(user, otpVerification.getVerificationCode());
        user.setActivated(true);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void changePassword(UpdatePasswordModel updatePasswordModel) {
        log.info("changePassword() called for {}", updatePasswordModel);
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        String hashedOldPass = authenticationHelper.hashPassword(updatePasswordModel.getOldPassword(), user.getAuthentication().getSalt());
        if (!hashedOldPass.equals(user.getAuthentication().getPassword())) {
            throw new InvalidOldPasswordException();
        }
        user.getAuthentication().setPassword(
                authenticationHelper.hashPassword(updatePasswordModel.getNewPassword(), user.getAuthentication().getSalt()));
        authenticationRepository.save(user.getAuthentication());
    }
}