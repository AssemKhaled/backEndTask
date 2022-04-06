package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    DriverService driverService;

    @GetMapping("/numberOfOnTrip")
    ResponseEntity<ApiResponse<Object>> numberOfOnTrip(@RequestParam Long userId){
        return driverService.analysis(userId,true);
    }
    @GetMapping("/numberOfIdle")
    ResponseEntity<ApiResponse<Object>> numberOfIdle(@RequestParam Long userId){
        return driverService.analysis(userId,false);
    }
}
