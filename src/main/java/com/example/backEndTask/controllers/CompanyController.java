package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyServiceImpl;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<Object>> companyRegister(@RequestBody CompanyRegistrationRequest companyRegistrationRequest) throws NoSuchAlgorithmException {
        return companyServiceImpl.companyRegistration(companyRegistrationRequest);
    }
    @PostMapping("/login")
    ResponseEntity<ApiResponse<Object>> companyLogin(@RequestBody CompanyLoginRequest companyLoginRequest) throws NoSuchAlgorithmException {
        return companyServiceImpl.companyLogin(companyLoginRequest);
    }

    @GetMapping("/companyLiveData")
    ResponseEntity<ApiResponse<Object>> companyLiveData(@RequestParam Long userId){
        return companyServiceImpl.companyLiveData(userId);
    }

}
