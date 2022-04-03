package com.example.backEndTask.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email" , nullable = false,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile_number")
    private String mobileNumber;


}
