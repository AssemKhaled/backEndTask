package com.example.backEndTask.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyLoginResponse {
    private Long id;
    private String userName;
}
