package com.example.backEndTask.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DummyResponse {
    private Double max;
    private Double min;
    private Double avg;
}
