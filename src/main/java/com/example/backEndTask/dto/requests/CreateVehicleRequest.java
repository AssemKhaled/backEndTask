package com.example.backEndTask.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateVehicleRequest {
    private String name;
    private String serialNum;
    private Long companyId;
}
