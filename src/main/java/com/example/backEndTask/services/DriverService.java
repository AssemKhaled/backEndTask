package com.example.backEndTask.services;

import com.example.backEndTask.dto.requests.*;
import com.example.backEndTask.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;

public interface DriverService {

    ResponseEntity<ApiResponse<Object>> addDriver(AddDriverRequest addDriverRequest) throws NoSuchAlgorithmException;
    ResponseEntity<ApiResponse<Object>> driverLogin(DriverLoginRequest driverLoginRequest) throws NoSuchAlgorithmException;
    ResponseEntity<ApiResponse<Object>> saveLiveData(String token, DriverLiveDataRequest driverLiveDataRequest) ;
    ResponseEntity<ApiResponse<Object>> assignDriverToCompany(DriverAssignToCompanyRequest driverAssignToCompanyRequest) ;

}
