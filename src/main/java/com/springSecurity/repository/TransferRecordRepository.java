package com.springSecurity.repository;

import com.springSecurity.entities.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRecordRepository extends JpaRepository<TransferRecord, Long> {
    List<TransferRecord> findByVehicleChassisNumber(String chassisNumber);
    List<TransferRecord> findByPlateNumberPlateNumber(String plateNumber);
}
