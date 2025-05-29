package com.springSecurity.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PlateNumberRequest {

    @Schema(description = "Plate number format like 'RAD123B'", example = "RAD123B")
    @NotBlank(message = "Plate number is required")
    @Pattern(regexp = "^R[A-Z]{2}\\d{3}[A-Z]$", message = "Invalid plate number format. Example: RAB123C")
    private String plateNumber;

    @Schema(description = "UUID of the user to assign this plate number to")
    private Long ownerId;
}
