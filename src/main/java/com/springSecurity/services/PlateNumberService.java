package com.springSecurity.services;

import com.springSecurity.entities.PlateNumber;
import com.springSecurity.entities.User;
import com.springSecurity.model.PlateNumberRequest;
import com.springSecurity.repository.PlateNumberRepository;
import com.springSecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlateNumberService {

    private final PlateNumberRepository plateNumberRepository;
    private final UserRepository userRepository;

    @Transactional
    public PlateNumber addPlateNumber(PlateNumberRequest request) {
        if (plateNumberRepository.existsByPlateNumber(request.getPlateNumber())) {
            throw new IllegalArgumentException("Plate number already exists");
        }

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        PlateNumber plate = PlateNumber.builder()
                .plateNumber(request.getPlateNumber())
                .owner(owner)
                .isAvailable(true)
                .issuedDate(LocalDate.now())
                .build();

        return plateNumberRepository.save(plate);
    }

    public Page<PlateNumber> getPlatesByOwner(Long ownerId, int page, int size) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Pageable pageable = PageRequest.of(page, size);
        return plateNumberRepository.findAllByOwner(owner, pageable); // ✅
    }


    public Page<PlateNumber> getAllPlates(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return plateNumberRepository.findAll(pageable);
    }
}
