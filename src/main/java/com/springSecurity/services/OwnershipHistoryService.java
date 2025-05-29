package com.springSecurity.services;

import com.springSecurity.entities.TransferRecord;
import com.springSecurity.model.OwnershipHistoryResponse;
import com.springSecurity.repository.TransferRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnershipHistoryService {

    private final TransferRecordRepository transferRecordRepository;

    public List<OwnershipHistoryResponse> getHistoryByChassisNumber(String chassisNumber) {
        List<TransferRecord> records = transferRecordRepository.findByVehicleChassisNumber(chassisNumber);

        return records.stream().map(record -> OwnershipHistoryResponse.builder()
                .oldOwnerName(record.getOldOwner().getFullName())
                .newOwnerName(record.getNewOwner().getFullName())
                .transferDate(record.getIssuedDate())
                .purchasePrice(record.getAmount())
                .plateNumber(record.getPlateNumber().getPlateNumber())
                .chassisNumber(record.getVehicle().getChassisNumber())
                .build()).collect(Collectors.toList());
    }

    public List<OwnershipHistoryResponse> getHistoryByPlateNumber(String plateNumber) {
        List<TransferRecord> records = transferRecordRepository.findByPlateNumberPlateNumber(plateNumber);

        return records.stream().map(record -> OwnershipHistoryResponse.builder()
                .oldOwnerName(record.getOldOwner().getFullName())
                .newOwnerName(record.getNewOwner().getFullName())
                .transferDate(record.getIssuedDate())
                .purchasePrice(record.getAmount())
                .plateNumber(record.getPlateNumber().getPlateNumber())
                .chassisNumber(record.getVehicle().getChassisNumber())
                .build()).collect(Collectors.toList());
    }
}
