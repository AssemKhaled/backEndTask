package com.example.backEndTask.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CompanyLoginRequest {
    private String  email;
    private String password;
}
