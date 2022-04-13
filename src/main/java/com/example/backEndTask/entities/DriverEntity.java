package com.example.backEndTask.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "driver")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_Number",nullable = false,unique = true)
    private String  phoneNumber ;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    private String token;

    @ManyToOne
    @JoinColumn(name="company_id")
    @JsonIgnore
    private CompanyEntity company;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle   ;


}
