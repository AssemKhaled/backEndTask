package com.example.backEndTask.controllers;


import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.Impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleSerivceimpl;

    @PostMapping("/create")
    ResponseEntity<ApiResponse<Object>> createVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
        return vehicleSerivceimpl.createVehicle(createVehicleRequest);
   }
}
