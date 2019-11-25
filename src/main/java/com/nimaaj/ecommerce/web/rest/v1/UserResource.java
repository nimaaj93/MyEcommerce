package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProfileDTO;
import com.nimaaj.ecommerce.dto.UserDTO;
import com.nimaaj.ecommerce.model.JWTToken;
import com.nimaaj.ecommerce.model.input.AuthenticateModel;
import com.nimaaj.ecommerce.model.input.OtpVerification;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.security.AuthenticationHelper;
import com.nimaaj.ecommerce.security.jwt.JWTFilter;
import com.nimaaj.ecommerce.security.jwt.TokenProvider;
import com.nimaaj.ecommerce.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthenticationHelper authenticationHelper;

    public UserResource(UserService userService,
                        TokenProvider tokenProvider,
                        AuthenticationManager authenticationManager,
                        UserRepository userRepository,
                        AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody AuthenticateModel model) {

        String hashPass = userRepository.findOneWithAuthoritiesByMobileNumberOrEmail(model.getUsername(), model.getUsername())
            .map(User::getAuthentication)
            .map(authentication -> authenticationHelper.hashPassword(model.getPassword(), authentication.getSalt()))
            .orElseThrow(() -> new BadCredentialsException("Invalid username or pass"));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(model.getUsername(), hashPass);

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, model.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody UserRegistrationModel model) {
        userService.register(model);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/activate")
    public ResponseEntity<JWTToken> activateUser(@Valid @RequestBody OtpVerification otpVerification) {
        UserDTO userDTO = userService.activateUser(otpVerification);
        //TODO add otp auth provider and move token issuing from resource
        return null;
    }

}