package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.requests.AddDriverRequest;
import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.Impl.DriverServiceImpl;
import com.example.backEndTask.services.Impl.VehicleSerivceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverServiceImpl driverServiceimpl;

    @PostMapping("/create")
    ResponseEntity<ApiResponse<Object>> addDriver(@RequestBody AddDriverRequest addDriverRequest) {
        return driverServiceimpl.addDriver(addDriverRequest);
    }
}
