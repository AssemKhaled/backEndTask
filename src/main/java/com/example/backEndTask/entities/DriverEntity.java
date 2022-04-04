package com.example.backEndTask.entities;
import lombok.*;

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
}
