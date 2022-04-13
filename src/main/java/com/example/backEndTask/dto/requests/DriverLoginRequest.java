package com.example.backEndTask.dto.requests;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DriverLoginRequest {

    private String password;
    private String phoneNumber;
}
