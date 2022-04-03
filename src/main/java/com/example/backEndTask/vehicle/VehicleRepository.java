package com.example.backEndTask.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,Long> {

    VehicleEntity findBySerialNum(String serialNum);
}
