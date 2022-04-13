package com.example.backEndTask.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document(collection = "tc_positions" )
public class DummySarebMongo {

  @Id
  private ObjectId _id;

  private String protocol;

  private Long deviceId;

  private Date serverTime;

  private Date deviceTime;

  private Date fixTime;

  private Boolean valid;

  private Double latitude;

  private Double longitude;

  private Double altitude;

  private Double speed;

  private Double course;

  private String address;

  private Object attributes;

  private Double accuracy;

  private String network;

  private String deviceName;

  private String deviceReferenceKey;

  private String driverReferenceKey;

  private String driverName;

  private Long driverId;

  private Double weight;

}
