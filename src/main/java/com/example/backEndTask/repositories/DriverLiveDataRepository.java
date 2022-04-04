package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.DriverLiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLiveDataRepository extends MongoRepository<DriverLiveData,Long> {
}
