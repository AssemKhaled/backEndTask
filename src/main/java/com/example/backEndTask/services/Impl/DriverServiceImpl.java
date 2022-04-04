package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.requests.AddDriverRequest;
import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.requests.DriverLoginRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.CompanyLoginResponse;
import com.example.backEndTask.dto.response.DriverLoginResponse;
import com.example.backEndTask.entities.CompanyEntity;
import com.example.backEndTask.entities.DriverEntity;
import com.example.backEndTask.entities.VehicleEntity;
import com.example.backEndTask.repositories.DriverRepository;
import com.example.backEndTask.repositories.VehicleRepository;
import com.example.backEndTask.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;
    private String token = UUID.randomUUID().toString();

    @Override
    public ResponseEntity<ApiResponse<Object>> addDriver(AddDriverRequest addDriverRequest) throws NoSuchAlgorithmException {
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
        } else {

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
                                .body("ERROR NOT FOUND")
                                .statusCode(404)
                                .success(false)
                                .build());
                return failure;
            } else {
                DriverEntity driverEntity = optionalDriverEntity.get();
                String hashedPassword = hashMd5(driverLoginRequest.getPassword());


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

                    driverRepository.save(DriverEntity
                            .builder()
                            .token(token)
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

    private String hashMd5(String password) throws NoSuchAlgorithmException     {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHashPassword = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHashPassword;
    }
}
