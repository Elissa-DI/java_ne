package com.springSecurity.model;

import lombok.Data;

@Data

public class UpdateUserRequest {

    private  String phoneNumber;
    private   String fullName;
}
