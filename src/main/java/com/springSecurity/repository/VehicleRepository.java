package com.springSecurity.repository;

import com.springSecurity.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByChassisNumber(String chassisNumber);
}
