package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.CompanyLoginResponse;
import com.example.backEndTask.entities.CompanyEntity;
import com.example.backEndTask.repositories.CompanyRepository;
import com.example.backEndTask.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class CompanyServiceImpl  implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public ResponseEntity<ApiResponse<Object>> companyRegistration(CompanyRegistrationRequest companyRegistrationRequest) throws NoSuchAlgorithmException {

        if(companyRegistrationRequest.getEmail()==null|| companyRegistrationRequest.getEmail().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                    .builder()
                    .body("Failure")
                    .statusCode(400)
                    .success(false)
                    .build());
            return failure ;
        }else if(companyRegistrationRequest.getUserName()==null|| companyRegistrationRequest.getUserName().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                    .builder()
                    .body("Failure")
                    .statusCode(400)
                    .success(false)
                    .build());
            return failure ;
        }else if(companyRegistrationRequest.getPhoneNumber()==null|| companyRegistrationRequest.getPhoneNumber().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                    .builder()
                    .body("Failure")
                    .statusCode(400)
                    .success(false)
                    .build());
            return failure ;
        }else if(companyRegistrationRequest.getPassword()==null|| companyRegistrationRequest.getPassword().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                    .builder()
                    .body("Failure")
                    .statusCode(400)
                    .success(false)
                    .build());
            return failure ;
        }else {


            companyRepository.save(CompanyEntity
                    .builder()
                            .email(companyRegistrationRequest.getEmail())
                            .phoneNumber(companyRegistrationRequest.getPhoneNumber())
                            .password(hashMd5(companyRegistrationRequest.getPassword()))
                            .userName(companyRegistrationRequest.getUserName())
                    .build());

            ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                    ApiResponse
                            .builder()
                            .body("Success")
                            .statusCode(200)
                            .success(true)
                            .build());
            return success ;
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> companyLogin(CompanyLoginRequest companyLoginRequest) throws NoSuchAlgorithmException{
        if(companyLoginRequest.getEmail()==null|| companyLoginRequest.getEmail().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure ;
        }else if(companyLoginRequest.getPassword()==null|| companyLoginRequest.getPassword().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure ;
        }else {
            Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findByEmail(companyLoginRequest.getEmail());
            if (!optionalCompanyEntity.isPresent()) {
                ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                        ApiResponse
                                .builder()
                                .body("ERROR NOT FOUND")
                                .statusCode(404)
                                .success(false)
                                .build());
                return failure ;
            }
            else {
                CompanyEntity companyEntity = optionalCompanyEntity.get();
                String hashedPassword = hashMd5(companyLoginRequest.getPassword());
                if (hashedPassword.equals(companyEntity.getPassword())) {
                    ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                            ApiResponse
                                    .builder()
                                    .body(CompanyLoginResponse
                                            .builder()
                                            .id(companyEntity.getId())
                                            .userName(companyEntity.getUserName())
                                            .build())
                                    .statusCode(200)
                                    .success(true)
                                    .build());
                    return success ;
                }
                else {
                    ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                            ApiResponse
                                    .builder()
                                    .body("Login Failed unauthorized user or password")
                                    .statusCode(401)
                                    .success(false)
                                    .build());
                    return failure ;
                }
            }
        }
    }



    private String hashMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHashPassword = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHashPassword;
    }
}
