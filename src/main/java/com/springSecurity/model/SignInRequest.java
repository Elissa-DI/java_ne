package com.springSecurity.model;

import lombok.Data;

@Data
public class SignInRequest {
    private  String email;
    private  String password;
}
