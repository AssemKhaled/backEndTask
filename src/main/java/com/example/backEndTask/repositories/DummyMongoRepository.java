package com.example.backEndTask.repositories;

import com.example.backEndTask.entities.DummyMongo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyMongoRepository extends MongoRepository<DummyMongo, Long> {

        @Aggregation(pipeline = { "{$group: { _id: null, total: {$max: $price }}}" })
         double max();

        @Aggregation(pipeline = { "{$group: { _id: null, total: {$min: $price }}}" })
         double min();

        @Aggregation(pipeline = {"{$group: { _id: null, total: {$avg: '$price' }}}"})
        double avg();
}
