package com.springSecurity.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Old Owner ID is required")
    private Long oldOwnerId;

    @NotNull(message = "New Owner ID is required")
    private Long newOwnerId;

    @NotNull(message = "New Plate Number ID is required")
    private Long newPlateNumberId;

    @NotNull(message = "Transfer amount is required")
    @Min(value = 0, message = "Amount must be non-negative")
    private Double amount;
}
