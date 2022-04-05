package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity,Long> {

    Optional<DriverEntity> findByPhoneNumber(String phoneNumber);
    Optional<DriverEntity> findByToken(String token);

}
