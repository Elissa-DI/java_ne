package com.springSecurity.controller;

import com.springSecurity.model.JWtAuthenticationResponse;
import com.springSecurity.model.RefreshTokenRequest;
import com.springSecurity.model.SignInRequest;
import com.springSecurity.model.SignUpRequest;
import com.springSecurity.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "login register ")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    //sign up controller
    @Operation(
            description = " Register user and send email  that notify you that you have created account and you  with credentials you  have created with ",
            summary = "this is summary for  register user with email, password , phone number, full name and national ID",
            responses = {
                    @ApiResponse(
                            description = "success  returns new user who have been created",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "user with that email already exists",
                            responseCode = "409"

                    ),
                    @ApiResponse(
                            description = "provide invalid credentials",
                            responseCode = "401"

                    )
            })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid  @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));


    }

    //    this is controller that implement login of user by providing password and email
    @Operation(
            description = " Login user ",
            summary = "this is summary for how  login work you must provide password and email",
            responses = {
                    @ApiResponse(
                            description = "success it return  credentials      by adding token and refreshing token",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "invalid credentials",
                            responseCode = "401"

                    )
            })
    @PostMapping("/signin")
    public ResponseEntity<JWtAuthenticationResponse> signin(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    //     generate new token using refresh token
    @Operation(
            description = "generate new token ",
            summary = "this is summary for generate new token without login , by  using refresh token",
            responses = {
                    @ApiResponse(
                            description = "success it return  new token  and refresh token",
                            responseCode = "200"

                    ),
                    @ApiResponse(
                            description = "invalid token or parsing token have failed ",
                            responseCode = "401"

                    )
            })
    @PostMapping("/refresh")
    public ResponseEntity<JWtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
