package com.springSecurity.services.impl;


import com.springSecurity.model.JWtAuthenticationResponse;
import com.springSecurity.model.RefreshTokenRequest;
import com.springSecurity.model.SignInRequest;
import com.springSecurity.model.SignUpRequest;
import com.springSecurity.entities.Role;
import com.springSecurity.entities.User;
import com.springSecurity.errors.exception.ApiRequestException;
import com.springSecurity.repository.UserRepository;
import com.springSecurity.services.AuthenticationService;
import com.springSecurity.services.EmailService;
import com.springSecurity.services.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    //     delete  user
    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());
        log.info(signUpRequest.toString());
        boolean userExists = userRepository.findByEmail(signUpRequest.getEmail()).isPresent();
        if (userExists) {
            throw new ApiRequestException(" user already exists", HttpStatus.CONFLICT);
//
        } else {
            String subject = "Welcome to a World of Possibilities! 🚀";
            String text = "Dear " + signUpRequest.getFullName() + "\n\n" +
                    "🌟 Congratulations on joining our vibrant API community! We are delighted to have you on board, and we can't wait to see the incredible projects you'll create with our APIs.\n\n" +
                    "🚀 Your journey starts now. Explore the limitless potential of our APIs by visiting [API Documentation](https://localhost:8080/swagger-ui.html).\n\n" +
                    "🔧 Got questions or need assistance? Our dedicated team is here to help. Don't hesitate to reach out!\n\n" +
                    "🌐 Stay connected with us:\n" +
                    "LinkedIn: [Follow us on LinkedIn](https://www.linkedin.com/in/elissa-dusabe-415161256//)\n" +
                    "GitHub: [Check out our repositories on GitHub](https://github.com/Elissa_DI)\n" +
                    "👉 Let's make your projects shine together!\n\n" +
                    "Best regards,\n" +
                    "Elissa Company Group , here credentials you have created for your account " + signUpRequest;


            emailService.sendSimpleEmailMessage(signUpRequest.getEmail(), subject, text);
            return userRepository.save(user);

        }

    }

    //     sign in user
    @Override
    public JWtAuthenticationResponse signin(SignInRequest signinRequest) {


        try {
//            authentication manager
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            get logged user
            var user = (User) authentication.getPrincipal();
//            generate token
            var jwt = jwtService.generateToken(user);
//            generate refresh token
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
//            response
            return JWtAuthenticationResponse.builder()
                    .refreshToken(refreshToken)
                    .token(jwt)
                    .user(user)
                    .build();
        } catch (BadCredentialsException ex) {
            throw new ApiRequestException(" invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (LockedException ex) {
            throw new ApiRequestException(ex.getMessage(), HttpStatus.UNAUTHORIZED);

        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage(), HttpStatus.UNAUTHORIZED);

        }
    }

    //     get refresh token
    public JWtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getRefreshToken());
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ApiRequestException(" user not found", HttpStatus.NOT_FOUND));

        if (jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
            var jwt = jwtService.generateToken(user);

            return JWtAuthenticationResponse.builder()
                    .refreshToken(refreshTokenRequest.getRefreshToken())
                    .token(jwt)

                    .build();
        }
        return null;
    }
}
