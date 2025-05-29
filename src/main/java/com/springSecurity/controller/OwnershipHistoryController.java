package com.springSecurity.controller;

import com.springSecurity.model.OwnershipHistoryResponse;
import com.springSecurity.services.OwnershipHistoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class OwnershipHistoryController {

    private final OwnershipHistoryService ownershipHistoryService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/chassis/{chassisNumber}")
    public ResponseEntity<List<OwnershipHistoryResponse>> getHistoryByChassis(@PathVariable String chassisNumber) {
        return ResponseEntity.ok(ownershipHistoryService.getHistoryByChassisNumber(chassisNumber));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/plate/{plateNumber}")
    public ResponseEntity<List<OwnershipHistoryResponse>> getHistoryByPlate(@PathVariable String plateNumber) {
        return ResponseEntity.ok(ownershipHistoryService.getHistoryByPlateNumber(plateNumber));
    }
}
