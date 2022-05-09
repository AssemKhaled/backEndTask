package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

    Optional<CompanyEntity> findByEmail(String email);

//    @Query(value = "SELECT * FROM company where company.user_name like %:search% " , nativeQuery = true)
//    Optional<List<CompanyEntity>> findUserName (@Param("search") String search);
    Optional<List<CompanyEntity>> findAllByUserNameContaining(String search);
    List<CompanyEntity> findAll();
}
