package com.example.backEndTask.dto.response;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyLiveDataResponse {

    private String CompanyName;

    private String driverName;

    private Object attributes;

    private Double latitude;

    private Double longitude;

    private Boolean onTrip;

}
