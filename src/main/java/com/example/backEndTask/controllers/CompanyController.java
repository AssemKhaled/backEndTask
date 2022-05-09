package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.ApiResponseBuilder;
import com.example.backEndTask.entities.CompanyEntity;
import com.example.backEndTask.exception.ApiRequestException;
import com.example.backEndTask.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyServiceImpl;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<CompanyEntity>> companyRegister(@RequestBody CompanyRegistrationRequest companyRegistrationRequest) throws NoSuchAlgorithmException   {
        try{
            return ResponseEntity.ok(companyServiceImpl.companyRegistration(companyRegistrationRequest));

        }catch (Exception | Error e){
//            ApiResponseBuilder<CompanyEntity> builder = new ApiResponseBuilder<>();
//
//            builder.setMessage(e.getLocalizedMessage());
//            builder.setStatusCode(401);
//            builder.setBody(null);
//            builder.setSuccess(false);
             throw new ApiRequestException(e.getLocalizedMessage());
        }
//        return companyServiceImpl.companyRegistration(companyRegistrationRequest);
    }
    @PostMapping("/login")
    ResponseEntity<ApiResponse<Object>> companyLogin(@RequestBody CompanyLoginRequest companyLoginRequest) throws NoSuchAlgorithmException {
        return companyServiceImpl.companyLogin(companyLoginRequest);
    }

    @GetMapping("/companyLiveData")
    ResponseEntity<ApiResponse<Object>> companyLiveData(@RequestParam Long userId){
        return companyServiceImpl.companyLiveData(userId);
    }

    @GetMapping("/searchCompany")
    ResponseEntity<ApiResponse<Object>> searchCompany(@RequestParam String  search){
        throw new ApiRequestException("oops");
        //        return companyServiceImpl.searchCompany(search);
    }

}
