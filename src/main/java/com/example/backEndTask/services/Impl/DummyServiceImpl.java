package com.example.backEndTask.services.Impl;

import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.dto.response.DummyResponse;
import com.example.backEndTask.dto.response.OnTripResponse;
import com.example.backEndTask.repositories.DummyMongoRepository;
import com.example.backEndTask.services.DummyService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;


@Component
public class DummyServiceImpl implements DummyService {

    @Autowired
    private DummyMongoRepository dummyMongoRepository ;


    private  static final Logger log = LoggerFactory.getLogger(DummyServiceImpl.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Override
//    @Scheduled(fixedRate = 9000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }

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

    @Override
    public ResponseEntity<ApiResponse<Object>> restTemp(Long userId)  {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:8080/analysis/numberOfOnTrip?userId="+userId;
        Object response = restTemplate.getForObject(uri , Object.class);

        ResponseEntity<ApiResponse<Object>> result =ResponseEntity.ok(ApiResponse
                .builder()
                .body(response)
                .build());

        return result;
    }
}
