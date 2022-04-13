package com.example.backEndTask.dto.requests;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DriverLiveDataRequest {

    private Double latitude;
    private Double longitude;
    private Boolean onTrip;
}
