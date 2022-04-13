package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,Long> {

    Optional<VehicleEntity> findBySerialNum(String serialNum);
}
