package com.example.backEndTask.controllers;

import com.example.backEndTask.dto.requests.AddDriverRequest;
import com.example.backEndTask.dto.requests.DriverAssignToCompanyRequest;
import com.example.backEndTask.dto.requests.DriverLiveDataRequest;
import com.example.backEndTask.dto.requests.DriverLoginRequest;
import com.example.backEndTask.dto.response.ApiResponse;
import com.example.backEndTask.services.Impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverServiceImpl driverServiceimpl;

    @PostMapping("/create")
    ResponseEntity<ApiResponse<Object>> addDriver(@RequestBody AddDriverRequest addDriverRequest) throws NoSuchAlgorithmException {
        return driverServiceimpl.addDriver(addDriverRequest);
    }
    @PostMapping("/login")
    ResponseEntity<ApiResponse<Object>> driverLogin(@RequestBody DriverLoginRequest driverLoginRequest) throws NoSuchAlgorithmException {
        return driverServiceimpl.driverLogin(driverLoginRequest);
    }
    @PostMapping("/saveLiveData")
    ResponseEntity<ApiResponse<Object>> saveLiveData(@RequestHeader String token, @RequestBody DriverLiveDataRequest driverLiveDataRequest)  {
      return driverServiceimpl.saveLiveData(token,driverLiveDataRequest);
   }
    @PostMapping("/assignDriverToCompany")
    ResponseEntity<ApiResponse<Object>> assignDriverToCompany(@RequestBody DriverAssignToCompanyRequest driverAssignToCompanyRequest)  {
        return driverServiceimpl.assignDriverToCompany(driverAssignToCompanyRequest);
    }
    @PostMapping("/restTempDriver")
    ResponseEntity<ApiResponse<Object>> restTempDriver(@RequestBody AddDriverRequest addDriverRequest) {
        return driverServiceimpl.restTempDriver(addDriverRequest);
    }
}
