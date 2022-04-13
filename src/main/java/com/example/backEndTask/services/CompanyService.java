package com.example.backEndTask.services;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;

public interface CompanyService {
    ResponseEntity<ApiResponse<Object>> companyRegistration(CompanyRegistrationRequest companyRegistrationRequest) throws NoSuchAlgorithmException;
    ResponseEntity<ApiResponse<Object>> companyLogin(CompanyLoginRequest companyLoginRequest) throws NoSuchAlgorithmException;
}
