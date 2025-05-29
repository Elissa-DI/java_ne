package com.springSecurity.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OwnershipHistoryResponse {
    private String oldOwnerName;
    private String newOwnerName;
    private LocalDateTime transferDate;
    private BigDecimal purchasePrice;
    private String plateNumber;
    private String chassisNumber;
}
