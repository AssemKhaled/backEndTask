package com.example.backEndTask.services;

import com.example.backEndTask.dto.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;


public interface DummyService {

    ResponseEntity<ApiResponse<Object>> amm();

    ResponseEntity<ApiResponse<Object>> restTemp(Long userId) ;
//    void reportCurrentTime();

}
