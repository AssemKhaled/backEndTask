package com.example.backEndTask.services;

import com.example.backEndTask.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;


public interface DummyService {

    ResponseEntity<ApiResponse<Object>> amm();

    ResponseEntity<ApiResponse<Object>> restTemp(Long userId);
}
