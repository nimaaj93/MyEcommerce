package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Authentication;
import com.nimaaj.ecommerce.domain.Authority;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProfileDTO;
import com.nimaaj.ecommerce.dto.UserDTO;
import com.nimaaj.ecommerce.enumaration.UserRole;
import com.nimaaj.ecommerce.enumaration.UserType;
import com.nimaaj.ecommerce.exception.AuthorityNotFoundException;
import com.nimaaj.ecommerce.exception.InvalidCurrentPassException;
import com.nimaaj.ecommerce.exception.UserExistsException;
import com.nimaaj.ecommerce.exception.UserNotFoundException;
import com.nimaaj.ecommerce.mapper.UserMapper;
import com.nimaaj.ecommerce.model.input.OtpVerification;
import com.nimaaj.ecommerce.model.input.ResetPassModel;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;
import com.nimaaj.ecommerce.repository.AuthenticationRepository;
import com.nimaaj.ecommerce.repository.AuthorityRepository;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.security.AuthenticationHelper;
import com.nimaaj.ecommerce.service.AuthenticationService;
import com.nimaaj.ecommerce.service.OtpService;
import com.nimaaj.ecommerce.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationService authenticationService;
    private final AuthenticationHelper authenticationHelper;
    private final UserMapper userMapper;
    private final OtpService otpService;

    public UserServiceImpl(UserRepository userRepository,
                           AuthenticationRepository authenticationRepository,
                           AuthorityRepository authorityRepository,
                           AuthenticationService authenticationService,
                           AuthenticationHelper authenticationHelper,
                           UserMapper userMapper, OtpService otpService) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
        this.authorityRepository = authorityRepository;
        this.authenticationService = authenticationService;
        this.authenticationHelper = authenticationHelper;
        this.userMapper = userMapper;
        this.otpService = otpService;
    }

    @Override
    public ProfileDTO getProfile() {
        return null;
    }

    @Override
    @Transactional
    public UserDTO register(UserRegistrationModel model) {

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
    public void resetPass(ResetPassModel model) {

        String currentHash = authenticationService.hashPassForCurrentUser(model.getCurrentPassword());
        Authentication authentication = authenticationService.getCurrentAuthentication();

        if (!authentication.getPassword().equals(currentHash)) {
            throw new InvalidCurrentPassException();
        }

        String newHash = authenticationService.hashPassForCurrentUser(model.getNewPassword());
        authentication.setPassword(newHash);

        authenticationRepository.save(authentication);
    }

    @Override
    public UserDTO activateUser(OtpVerification otpVerification) {
        User user = userRepository.findOneWithAuthoritiesByMobileNumber(otpVerification.getMobileNumber())
                .orElseThrow(UserNotFoundException::new);
        otpService.verifyOtpForUser(user, otpVerification.getVerificationCode());
        user.setActivated(true);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
