package com.example.backEndTask.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document("driver_live_data")
public class DriverLiveData {
    @Id
    @Field(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(name = "latitude")
    private Double latitude;

    @Field(name = "longitude")
    private Double longitude;

    @Field(name = "driver_id")
    private Double driverId;

    @Field(name = "on_trip")
    private Boolean onTrip;

}
