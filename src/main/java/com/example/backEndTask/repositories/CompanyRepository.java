package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

    Optional<CompanyEntity> findByEmail(String email);
}