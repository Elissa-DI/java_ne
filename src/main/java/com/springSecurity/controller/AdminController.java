package com.springSecurity.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Hidden
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<String> getAdmin(String heder){


        return  ResponseEntity.ok().body("Hello Admin");

    }
}
