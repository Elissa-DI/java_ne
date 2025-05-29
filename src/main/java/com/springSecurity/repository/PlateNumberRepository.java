package com.springSecurity.repository;

import com.springSecurity.entities.PlateNumber;
import com.springSecurity.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlateNumberRepository extends JpaRepository<PlateNumber, Long> {
    List<PlateNumber> findByOwner(User owner);
    Page<PlateNumber> findAllByOwner(User owner, Pageable pageable);
    boolean existsByPlateNumber(String plateNumber);
}
