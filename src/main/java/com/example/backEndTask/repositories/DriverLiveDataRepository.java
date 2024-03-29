package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.DriverLiveDataMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverLiveDataRepository extends MongoRepository<DriverLiveDataMongo,Long> {

    Optional<DriverLiveDataMongo> findByDriverId(Long driverId);

    long countAllByOnTripAndDriverId(Boolean onTrip, Long driverId);

    Optional<DriverLiveDataMongo> findTopByDriverIdOrderByTimeStampDesc( Long driverId);

    Optional<List<DriverLiveDataMongo>> findAllByDriverIdIn(List<Long> driverIds);

}
