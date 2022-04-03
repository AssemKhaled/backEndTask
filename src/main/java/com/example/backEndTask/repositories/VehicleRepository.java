package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity,Long> {
}
