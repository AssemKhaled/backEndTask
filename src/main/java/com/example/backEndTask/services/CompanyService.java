package com.example.backEndTask.services;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.entities.CompanyEntity;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;

public interface CompanyService {
    ApiResponse<CompanyEntity> companyRegistration(CompanyRegistrationRequest companyRegistrationRequest) throws NoSuchAlgorithmException;
    ResponseEntity<ApiResponse<Object>> companyLogin(CompanyLoginRequest companyLoginRequest) throws NoSuchAlgorithmException;

    ResponseEntity<ApiResponse<Object>> companyLiveData(Long userId);

    ResponseEntity<ApiResponse<Object>> searchCompany(String search);

}
