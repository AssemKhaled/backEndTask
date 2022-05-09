package com.example.backEndTask.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse<T> {

    private Integer statusCode;
    private Boolean success;
    private String message;
    private T body;
}
