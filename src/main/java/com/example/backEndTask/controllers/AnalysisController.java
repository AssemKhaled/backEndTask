package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.DriverService;
import com.example.backEndTask.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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
