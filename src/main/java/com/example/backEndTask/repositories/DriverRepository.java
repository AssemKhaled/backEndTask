package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverEntity,Long> {
}
