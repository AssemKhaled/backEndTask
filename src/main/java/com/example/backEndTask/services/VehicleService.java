package com.example.backEndTask.services;


import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface VehicleService {


    ResponseEntity<ApiResponse<Object>> createVehicle(CreateVehicleRequest createVehicleRequest);
}
