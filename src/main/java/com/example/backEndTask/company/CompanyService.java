package com.example.backEndTask.company;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

}
