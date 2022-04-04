package com.example.backEndTask.services;

import com.example.backEndTask.dto.requests.AddDriverRequest;
import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.requests.DriverLoginRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;

public interface DriverService {

    ResponseEntity<ApiResponse<Object>> addDriver(AddDriverRequest addDriverRequest) throws NoSuchAlgorithmException;
    ResponseEntity<ApiResponse<Object>> driverLogin(DriverLoginRequest driverLoginRequest) throws NoSuchAlgorithmException;

}
