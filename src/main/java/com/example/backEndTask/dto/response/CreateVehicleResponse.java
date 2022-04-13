package com.example.backEndTask.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateVehicleResponse {
    private Long id;
    private String  name;
    private String serialNum;
}
