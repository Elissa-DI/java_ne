package com.springSecurity.services;

import com.springSecurity.entities.*;
import com.springSecurity.model.TransferRequest;
import com.springSecurity.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRecordRepository transferRecordRepository;
    private final VehicleRepository vehicleRepository;
    private final PlateNumberRepository plateNumberRepository;
    private final UserRepository userRepository;

    @Transactional
    public TransferRecord transferOwnership(TransferRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        User oldOwner = userRepository.findById(request.getOldOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Old owner not found"));

        User newOwner = userRepository.findById(request.getNewOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("New owner not found"));

        PlateNumber newPlate = plateNumberRepository.findById(request.getNewPlateNumberId())
                .orElseThrow(() -> new EntityNotFoundException("Plate number not found"));

        if (!newPlate.isAvailable()) {
            throw new IllegalStateException("Selected plate number is already used");
        }

        PlateNumber oldPlate = vehicle.getPlateNumber();
        if (oldPlate != null) {
            oldPlate.setAvailable(true); // make old plate available again
        }

        newPlate.setAvailable(false);
        vehicle.setOwner(newOwner);
        vehicle.setPlateNumber(newPlate);

        TransferRecord transfer = TransferRecord.builder()
                .issuedDate(LocalDateTime.now())
                .vehicle(vehicle)
                .oldOwner(oldOwner)
                .newOwner(newOwner)
                .plateNumber(newPlate)
                .amount(BigDecimal.valueOf(request.getAmount()))
                .build();

        return transferRecordRepository.save(transfer);
    }
}
