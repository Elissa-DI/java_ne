package com.springSecurity.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VehicleRequest {

    @NotBlank(message = "Chassis number is required")
    private String chassisNumber;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @Min(value = 1900, message = "Year must be a valid year")
    @Max(value = 2025, message = "Year must be a valid year")
    private int year;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Model name is required")
    private String modelName;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    @NotNull(message = "Plate ID is required")
    private Long plateId;
}
