package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.DriverService;
import com.example.backEndTask.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    DriverService driverService;
    @Autowired
    DummyService dummyService;


    @GetMapping("/numberOfOnTrip")
    ResponseEntity<ApiResponse<Object>> numberOfOnTrip(@RequestParam Long userId){
        return driverService.analysis(userId,true);
    }
    @GetMapping("/numberOfIdle")
    ResponseEntity<ApiResponse<Object>> numberOfIdle(@RequestParam Long userId){
        return driverService.analysis(userId,false);
    }

    @GetMapping("/avg")
    ResponseEntity<ApiResponse<Object>> avg(){
        return dummyService.amm();
    }
}
