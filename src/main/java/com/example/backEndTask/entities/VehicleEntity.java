package com.example.backEndTask.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name;

    @Column(name = "serial_num",nullable = false,unique = true)
    private String serialNum;

    @ManyToOne
    @JoinColumn(name="company_id")
    private CompanyEntity company;


}
