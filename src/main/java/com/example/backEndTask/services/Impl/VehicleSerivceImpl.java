package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.requests.CreateVehicleRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.CreateVehicleResponse;
import com.example.backEndTask.entities.VehicleEntity;
import com.example.backEndTask.repositories.VehicleRepository;
import com.example.backEndTask.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleSerivceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

  @Override
   public ResponseEntity<ApiResponse<Object>> createVehicle(CreateVehicleRequest createVehicleRequest){

      if (createVehicleRequest.getName()==null| createVehicleRequest.getName().equals("")) {
          ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                  ApiResponse
                          .builder()
                          .body("Failure")
                          .statusCode(400)
                          .success(false)
                          .build());
          return failure ;

      }else if(createVehicleRequest.getSerialNum()==null|| createVehicleRequest.getSerialNum().equals("")){
          ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                  ApiResponse
                          .builder()
                          .body("Failure")
                          .statusCode(400)
                          .success(false)
                          .build());
          return failure ;
      }else {

          vehicleRepository.save(VehicleEntity
                  .builder()
                  .name(createVehicleRequest.getName())
                  .serialNum(createVehicleRequest.getSerialNum())
                  .build());
          Optional<VehicleEntity> optionalVehicleEntity = vehicleRepository.findBySerialNum(createVehicleRequest.getSerialNum());
          VehicleEntity vehicleEntity = optionalVehicleEntity.get();
          String serial = createVehicleRequest.getSerialNum();
          if(serial.equals(vehicleEntity.getSerialNum())) {
          ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                  ApiResponse
                          .builder()
                          .body(CreateVehicleResponse
                                  .builder()
                                  .id(vehicleEntity.getId())
                                  .name(vehicleEntity.getName())
                                  .serialNum(vehicleEntity.getSerialNum())
                                  .build())
                          .statusCode(200)
                          .success(true)
                          .build());
          return success ;
          }else {
              ResponseEntity<ApiResponse<Object>> failure = ResponseEntity.badRequest().body(
                      ApiResponse
                              .builder()
                              .body("Serial Doesn`t Exists")
                              .statusCode(401)
                              .success(false)
                              .build());
              return failure ;
          }
      }

  }
}
