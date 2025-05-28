package com.springSecurity.services;


import com.springSecurity.model.JWtAuthenticationResponse;
import com.springSecurity.model.RefreshTokenRequest;
import com.springSecurity.model.SignInRequest;
import com.springSecurity.model.SignUpRequest;
import com.springSecurity.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JWtAuthenticationResponse signin(SignInRequest signInRequest);
    public  JWtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
