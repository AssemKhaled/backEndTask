package com.example.backEndTask.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document(collection ="dummy_data" )
public class DummyMongo {

    @Id
    private ObjectId _id;
    private String item;
    private Double price;

}
