package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.requests.AddDriverRequest;
import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.entities.DriverEntity;
import com.example.backEndTask.entities.VehicleEntity;
import com.example.backEndTask.repositories.DriverRepository;
import com.example.backEndTask.repositories.VehicleRepository;
import com.example.backEndTask.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> addDriver(AddDriverRequest addDriverRequest) {
        if (addDriverRequest.getPhoneNumber() == null || addDriverRequest.getPhoneNumber().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (addDriverRequest.getName() == null | addDriverRequest.getName().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;

        } else if (addDriverRequest.getPassword() == null || addDriverRequest.getPassword().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else {

            driverRepository.save(DriverEntity
                    .builder()
                    .name(addDriverRequest.getName())
                    .phoneNumber(addDriverRequest.getPhoneNumber())
                    .password(addDriverRequest.getPassword())
                    .build());

            ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                    ApiResponse
                            .builder()
                            .body("Success")
                            .statusCode(200)
                            .success(true)
                            .build());
            return success;
        }
    }
}
