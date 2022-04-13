package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
