package com.springSecurity.services;


import com.springSecurity.model.ResetPasswordRequest;
import com.springSecurity.model.UpdatePasswordResponse;
import com.springSecurity.model.UpdateUserRequest;
import com.springSecurity.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
 UserDetails getUser( );
    com.springSecurity.entities.User updateUser(UpdateUserRequest updateUserRequest);

    Optional<com.springSecurity.entities.User> deleteUSer() ;
    List<User> getAllUsers();
    UpdatePasswordResponse updatePassword (ResetPasswordRequest resetPasswordRequest);
}
