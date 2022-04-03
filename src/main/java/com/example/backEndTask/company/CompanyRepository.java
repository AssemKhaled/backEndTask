package com.example.backEndTask.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CompanyRepository
        extends JpaRepository<CompanyEntity,Long> {

    Optional<CompanyEntity> findByEmail(String email);
}
