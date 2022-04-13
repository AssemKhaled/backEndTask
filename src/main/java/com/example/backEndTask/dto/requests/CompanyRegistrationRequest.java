package com.example.backEndTask.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CompanyRegistrationRequest {
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
}
