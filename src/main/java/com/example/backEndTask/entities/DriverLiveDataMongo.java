package com.example.backEndTask.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document(collection ="driver_live_data" )
public class DriverLiveDataMongo {

    @Id
    private ObjectId _id;

    @Field(name = "latitude")
    private Double latitude;

    @Field(name = "longitude")
    private Double longitude;

    @Field(name = "driver_id")
    private Long driverId;

    @Field(name = "on_trip")
    private Boolean onTrip;

}
