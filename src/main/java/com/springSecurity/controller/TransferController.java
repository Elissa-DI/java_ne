package com.springSecurity.controller;

import com.springSecurity.entities.TransferRecord;
import com.springSecurity.model.TransferRequest;
import com.springSecurity.services.TransferService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<TransferRecord> transferVehicle(@Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(transferService.transferOwnership(request));
    }
}
