package com.springSecurity.controller;

import com.springSecurity.entities.PlateNumber;
import com.springSecurity.model.PlateNumberRequest;
import com.springSecurity.services.PlateNumberService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plates")
@RequiredArgsConstructor
public class PlateNumberController {

    private final PlateNumberService plateNumberService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add")
    public ResponseEntity<PlateNumber> addPlate(@Valid @RequestBody PlateNumberRequest request) {
        return ResponseEntity.ok(plateNumberService.addPlateNumber(request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Page<PlateNumber>> getByOwner(@PathVariable Long ownerId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(plateNumberService.getPlatesByOwner(ownerId, page, size));
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<Page<PlateNumber>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(plateNumberService.getAllPlates(page, size));
    }
}
