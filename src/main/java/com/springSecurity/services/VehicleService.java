package com.springSecurity.services;

import com.springSecurity.entities.PlateNumber;
import com.springSecurity.entities.User;
import com.springSecurity.entities.Vehicle;
import com.springSecurity.model.VehicleRequest;
import com.springSecurity.repository.PlateNumberRepository;
import com.springSecurity.repository.UserRepository;
import com.springSecurity.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final PlateNumberRepository plateNumberRepository;

    @Transactional
    public Vehicle registerVehicle(VehicleRequest request) {
        if (vehicleRepository.existsByChassisNumber(request.getChassisNumber())) {
            throw new IllegalArgumentException("Chassis number already exists");
        }

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        PlateNumber plate = plateNumberRepository.findById(request.getPlateId())
                .orElseThrow(() -> new EntityNotFoundException("Plate number not found"));

        if (!plate.isAvailable()) {
            throw new IllegalStateException("Plate number is already used");
        }

        plate.setAvailable(false); // mark plate as used

        Vehicle vehicle = new Vehicle();
        vehicle.setChassisNumber(request.getChassisNumber());
        vehicle.setManufacturer(request.getManufacturer());
        vehicle.setYear(request.getYear());
        vehicle.setPrice(BigDecimal.valueOf(request.getPrice())); // FIX: Convert double to BigDecimal
        vehicle.setModelName(request.getModelName());
        vehicle.setOwner(owner);
        vehicle.setPlateNumber(plate);

        return vehicleRepository.save(vehicle);


    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
