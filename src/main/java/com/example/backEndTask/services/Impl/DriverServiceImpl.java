package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.requests.AddDriverRequest;
import com.example.backEndTask.dto.requests.DriverAssignToCompanyRequest;
import com.example.backEndTask.dto.requests.DriverLiveDataRequest;
import com.example.backEndTask.dto.requests.DriverLoginRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.DriverLoginResponse;
import com.example.backEndTask.dto.response.IdleResponse;
import com.example.backEndTask.dto.response.OnTripResponse;
import com.example.backEndTask.entities.CompanyEntity;
import com.example.backEndTask.entities.DriverEntity;
import com.example.backEndTask.entities.DriverLiveDataMongo;
import com.example.backEndTask.repositories.CompanyRepository;
import com.example.backEndTask.repositories.DriverLiveDataRepository;
import com.example.backEndTask.repositories.DriverRepository;
import com.example.backEndTask.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverLiveDataRepository driverLiveDataRepository;
    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public ResponseEntity<ApiResponse<Object>> addDriver(AddDriverRequest addDriverRequest) throws NoSuchAlgorithmException {

        Optional<DriverEntity> optionalDriverEntity=driverRepository.findByPhoneNumber(addDriverRequest.getPhoneNumber());

        if (addDriverRequest.getPhoneNumber() == null || addDriverRequest.getPhoneNumber().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (addDriverRequest.getName() == null | addDriverRequest.getName().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;

        } else if (addDriverRequest.getPassword() == null || addDriverRequest.getPassword().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } if(optionalDriverEntity.isPresent()) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Phone Number Already Exists")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure ;
        }else {

            driverRepository.save(DriverEntity
                    .builder()
                    .name(addDriverRequest.getName())
                    .phoneNumber(addDriverRequest.getPhoneNumber())
                    .password(hashMd5(addDriverRequest.getPassword()))
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
    public ResponseEntity<ApiResponse<Object>> driverLogin(DriverLoginRequest driverLoginRequest) throws NoSuchAlgorithmException {

        if(driverLoginRequest.getPhoneNumber()==null|| driverLoginRequest.getPhoneNumber().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure ;
        }else if(driverLoginRequest.getPassword()==null|| driverLoginRequest.getPassword().equals("")){
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("Failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure ;
        }else {
            Optional<DriverEntity> optionalDriverEntity = driverRepository.findByPhoneNumber(driverLoginRequest.getPhoneNumber());
            if (!optionalDriverEntity.isPresent()) {
                ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                        ApiResponse
                                .builder()
                                .body("Login Failed unauthorized user or password")
                                .statusCode(401)
                                .success(false)
                                .build());
                return failure;
            } else {
                DriverEntity driverEntity = optionalDriverEntity.get();
                String hashedPassword = hashMd5(driverLoginRequest.getPassword());
                String token = UUID.randomUUID().toString();

                if (hashedPassword.equals(driverEntity.getPassword())) {
                    ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                            ApiResponse
                                    .builder()
                                    .body(DriverLoginResponse
                                            .builder()
                                            .token(token)
                                            .build())
                                    .statusCode(200)
                                    .success(true)
                                    .build());

                    driverEntity.setToken(token);
                    driverRepository.save(driverEntity);

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
    public ResponseEntity<ApiResponse<Object>> saveLiveData(String token, DriverLiveDataRequest driverLiveDataRequest) {

        if (driverLiveDataRequest.getLatitude() == null || driverLiveDataRequest.getLatitude().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (driverLiveDataRequest.getLongitude() == null || driverLiveDataRequest.getLongitude().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (driverLiveDataRequest.getOnTrip() == null || driverLiveDataRequest.getOnTrip().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        }else{
            Optional<DriverEntity> optionalDriverEntity = driverRepository.findByToken(token);
            LocalDateTime date = LocalDateTime.now();

            if (optionalDriverEntity.isPresent()) {

                DriverEntity driverEntity = optionalDriverEntity.get();

                driverLiveDataRepository.save(DriverLiveDataMongo
                        .builder()
                        .latitude(driverLiveDataRequest.getLatitude())
                        .longitude(driverLiveDataRequest.getLongitude())
                        .onTrip(driverLiveDataRequest.getOnTrip())
                        .driverId(driverEntity.getId())
                        .timeStamp(date)
                        .build());

                ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                        ApiResponse
                                .builder()
                                .body("Success")
                                .statusCode(200)
                                .success(true)
                                .build());
                return success;

            }else {
                ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                        ApiResponse
                                .builder()
                                .body("No Such Token Found")
                                .statusCode(401)
                                .success(false)
                                .build());
                return failure;
            }
        }

    }

    @Override
    public ResponseEntity<ApiResponse<Object>> assignDriverToCompany(DriverAssignToCompanyRequest driverAssignToCompanyRequest) {
        if (driverAssignToCompanyRequest.getCompanyId() == null || driverAssignToCompanyRequest.getCompanyId().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        } else if (driverAssignToCompanyRequest.getDriverId() == null || driverAssignToCompanyRequest.getDriverId().equals("")) {
            ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                    ApiResponse
                            .builder()
                            .body("failure")
                            .statusCode(400)
                            .success(false)
                            .build());
            return failure;
        }else {
            Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findById(driverAssignToCompanyRequest.getCompanyId());
            if (optionalCompanyEntity.isPresent()) {
                CompanyEntity companyEntity = optionalCompanyEntity.get();
                for (Long driverId : driverAssignToCompanyRequest.getDriverId()) {
                    Optional<DriverEntity> driverEntity = driverRepository.findById(driverId);
                    if (driverEntity.isPresent()) {
                        driverEntity.get().setCompany(companyEntity);
                        driverRepository.save(driverEntity.get());
                    }
                }
                ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                        ApiResponse
                                .builder()
                                .body("Success")
                                .statusCode(200)
                                .success(true)
                                .build());
                return success;
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
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> analysis(Long userId,Boolean onTrip) {


        Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findById(userId);
        if(optionalCompanyEntity.isPresent()){

            CompanyEntity companyEntity = optionalCompanyEntity.get();
            long numberOfTrips = 0 ;
            long numberOfIdle = 0 ;
            for (DriverEntity driverEntity : companyEntity.getDrivers()) {
                Optional<DriverLiveDataMongo> optionalDriverLiveDataMongo = driverLiveDataRepository.findTopByDriverIdOrderByTimeStampDesc(driverEntity.getId());
                if (optionalDriverLiveDataMongo.isPresent()) {
                    DriverLiveDataMongo driverLiveDataMongo = optionalDriverLiveDataMongo.get();
                    if (driverLiveDataMongo.getOnTrip() == true) {
                    if (driverLiveDataRepository.countAllByOnTripAndDriverId(onTrip, driverEntity.getId()) >= 1) {
                        numberOfTrips++;
                    }
                    }else if (driverLiveDataMongo.getOnTrip()==false) {
                        if (driverLiveDataRepository.countAllByOnTripAndDriverId(onTrip, driverEntity.getId()) >= 1) {
                            numberOfIdle++;
                        }

                    }
                }
            }

//            for (DriverEntity driverEntity : companyEntity.getDrivers()){
//                Optional<DriverLiveDataMongo> optionalDriverLiveDataMongo = driverLiveDataRepository.findFirstByOnTripAndDriverIdOrderByTimeStampDesc(onTrip,driverEntity.getId());
//                DriverLiveDataMongo driverLiveDataMongo = optionalDriverLiveDataMongo.get();
//                    if (driverLiveDataRepository.countAllByOnTripAndDriverId(onTrip, driverEntity.getId()) >= 1 ) {
//                        if (optionalCompanyEntity.isPresent()) {
//                            numberOfTrips++;
//                        }
//
//                    }
//                    else if (driverLiveDataRepository.countAllByOnTripAndDriverId(onTrip, driverEntity.getId()) >= 1 || driverLiveDataMongo.getOnTrip()==false) {
//                        numberOfIdle++;
//                    }
//            }

            Object response ;
            if(onTrip == true){
                response = OnTripResponse.builder().onTrips(numberOfTrips).build();
            }else {
                response = IdleResponse.builder().idle(numberOfIdle).build();
            }
            ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                    ApiResponse
                            .builder()
                            .body(response)
                            .statusCode(200)
                            .success(true)
                            .build());
            return success;
        }
        else {
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


    private String hashMd5(String password) throws NoSuchAlgorithmException     {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHashPassword = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHashPassword;
    }

}
