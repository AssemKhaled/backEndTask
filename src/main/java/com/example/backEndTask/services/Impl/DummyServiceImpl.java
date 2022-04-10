package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.DummyResponse;
import com.example.backEndTask.repositories.DummyMongoRepository;
import com.example.backEndTask.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService {

    @Autowired
    private DummyMongoRepository dummyMongoRepository ;

    @Override
    public ResponseEntity<ApiResponse<Object>> amm() {
//        System.out.println(dummyMongoRepository.avg()+"hi");
        ResponseEntity<ApiResponse<Object>> success = ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .body(DummyResponse
                                .builder()
                                .max(dummyMongoRepository.max())
                                .min(dummyMongoRepository.min())
                                .avg(dummyMongoRepository.avg())
                                .build())
                        .statusCode(200)
                        .success(true)
                        .build());
        return success ;
    }
}
