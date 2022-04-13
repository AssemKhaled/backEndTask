package com.example.backEndTask.dto.requests;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DriverAssignToCompanyRequest {

    private List<Long> driverId;
    private Long companyId;
}
