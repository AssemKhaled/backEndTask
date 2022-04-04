package com.example.backEndTask.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company")
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

    @Column(name = "phone_Number")
    private String phoneNumber;


}
