package com.example.backEndTask.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddDriverRequest {
    private String password;
    private String phoneNumber;
    private String name;
}
