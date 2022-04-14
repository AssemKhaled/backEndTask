package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.requests.CompanyLoginRequest;
import com.example.backEndTask.dto.requests.CompanyRegistrationRequest;
import com.example.backEndTask.dto.response.*;
import com.example.backEndTask.entities.CompanyEntity;
import com.example.backEndTask.entities.DriverEntity;
import com.example.backEndTask.entities.DriverLiveDataMongo;
import com.example.backEndTask.repositories.CompanyRepository;
import com.example.backEndTask.repositories.DriverLiveDataRepository;
import com.example.backEndTask.repositories.DriverRepository;
import com.example.backEndTask.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class CompanyServiceImpl  implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverLiveDataRepository driverLiveDataRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> companyRegistration(CompanyRegistrationRequest companyRegistrationRequest) throws NoSuchAlgorithmException {

        Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findByEmail(companyRegistrationRequest.getEmail());

        if (companyRegistrationRequest.getEmail() == null || companyRegistrationRequest.getEmail().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (companyRegistrationRequest.getUserName() == null || companyRegistrationRequest.getUserName().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (companyRegistrationRequest.getPhoneNumber() == null || companyRegistrationRequest.getPhoneNumber().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (companyRegistrationRequest.getPassword() == null || companyRegistrationRequest.getPassword().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (optionalCompanyEntity.isPresent()) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Email Already Exists")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else {

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
            return success;
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> companyLogin(CompanyLoginRequest companyLoginRequest) throws NoSuchAlgorithmException {
        if (companyLoginRequest.getEmail() == null || companyLoginRequest.getEmail().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (companyLoginRequest.getPassword() == null || companyLoginRequest.getPassword().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else {
            Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findByEmail(companyLoginRequest.getEmail());
            if (!optionalCompanyEntity.isPresent()) {
                ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                        ApiResponse
                                .builder()
                                .body("Login Failed unauthorized user or password")
                                .statusCode(401)
                                .success(false)
                                .build());
                return failure;
            } else {
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
                    return success;
                } else {
                    ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                            ApiResponse
                                    .builder()
                                    .body("Login Failed unauthorized user or password")
                                    .statusCode(401)
                                    .success(false)
                                    .build());
                    return failure;
                }
            }
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> companyLiveData(Long userId) {

        List<Object> result = new ArrayList<>();
        Object value;
        Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findById(userId);

        if (optionalCompanyEntity.isPresent()) {

            CompanyEntity companyEntity = optionalCompanyEntity.get();
            Optional<List<DriverEntity>> optionalDriverEntities = driverRepository.findAllByCompanyId(companyEntity.getId());
            List<DriverEntity> driverEntityRes = optionalDriverEntities.get();
            List<Long> ids = new ArrayList<>();
            List<String> names= new ArrayList<>();

            for (DriverEntity driverEntity : driverEntityRes) {
                ids.add(driverEntity.getId());
            }

            Optional<List<DriverLiveDataMongo>> optionalDriverLiveDataMongo = driverLiveDataRepository.findAllByDriverIdIn(ids);

            if (optionalDriverLiveDataMongo.isPresent()) {

                List<DriverLiveDataMongo> driverLiveDataMongo1 = optionalDriverLiveDataMongo.get();

                for (DriverLiveDataMongo driverLiveDataMongo : driverLiveDataMongo1) {
                    value = CompanyLiveDataResponse
                            .builder()
                            .CompanyName(companyEntity.getUserName())
                            .driverName(driverEntityRes.stream().filter(driverEntity -> driverEntity.getId().equals(driverLiveDataMongo.getDriverId())).findFirst().get().getName())
                            .latitude(driverLiveDataMongo.getLatitude())
                            .longitude(driverLiveDataMongo.getLongitude())
                            .onTrip(driverLiveDataMongo.getOnTrip())
                            .build();
                    result.add(value);
                }

                ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(

                        ApiResponse
                                .builder()
                                .body(result)
                                .statusCode(200)
                                .success(true)
                                .build());
                return success;

            } else {
                ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                        ApiResponse
                                .builder()
                                .body("No Drivers Exists")
                                .statusCode(401)
                                .success(false)
                                .build());
                return failure;
            }

        } else {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("No Such Id")
                            .statusCode(401)
                            .success(false)
                            .build());
            return failure;
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
//    //Map<String ,Object> result= new HashMap<>();
//    Object result ;
//    List<Object> res = new ArrayList<>();
//    Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findById(userId);
//
//
//        if(optionalCompanyEntity.isPresent()) {
//
//                CompanyEntity companyEntity = optionalCompanyEntity.get();
//                Optional<List<DriverEntity>> optionalDriverEntities = driverRepository.findAllByCompany_Id(companyEntity.getId());
//        List<DriverEntity> driverEntity3 = optionalDriverEntities.get();
//        System.out.println(optionalDriverEntities);
//        for (DriverEntity driverEntity : companyEntity.getDrivers()) {
//        Optional<DriverLiveDataMongo> optionalDriverLiveDataMongo = driverLiveDataRepository.findTopByDriverIdOrderByTimeStampDesc(driverEntity.getId());
//
//
//        if (optionalDriverLiveDataMongo.isPresent()) {
//
//        DriverLiveDataMongo driverLiveDataMongo = optionalDriverLiveDataMongo.get();
//
//        result=CompanyLiveDataResponse.builder()
//        .CompanyName(companyEntity.getUserName())
//        .driverName(driverEntity.getName())
//        .latitude(driverLiveDataMongo.getLatitude())
//        .longitude(driverLiveDataMongo.getLongitude())
//        .onTrip(driverLiveDataMongo.getOnTrip())
//        .build();
//
//        res.add(result);
////                    result.clear();
////                    result.put("CompanyName",companyEntity.getUserName());
////                    result.put("DriverName",driverEntity.getName());
////                    result.put("Latitude",driverLiveDataMongo.getLatitude());
////                    result.put("Longitude",driverLiveDataMongo.getLongitude());
////                    result.put("OnTrip",driverLiveDataMongo.getOnTrip());
////                    res.add(companyEntity.getUserName());
////                    res.add(driverEntity.getName());
////                    res.add(driverLiveDataMongo.getLatitude());
////                    res.add(driverLiveDataMongo.getLongitude());
////                    res.add(driverLiveDataMongo.getOnTrip());
//        }
//        }
//        ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
//        ApiResponse
//        .builder()
//        .body(driverEntity3)
//        .statusCode(200)
//        .success(true)
//        .build());
//        return success;
//        } else {
//        ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
//        ApiResponse
//        .builder()
//        .body("No Such Id")
//        .statusCode(401)
//        .success(false)
//        .build());
//        return failure;
//        }