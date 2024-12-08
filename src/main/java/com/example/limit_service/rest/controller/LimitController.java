package com.example.limit_service.rest.controller;

import com.example.limit_service.rest.api.LimitApi;
import com.example.limit_service.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitController implements LimitApi {

    private final LimitService limitService;

    public ResponseEntity<String> paymentProcess(long userId, long paymentAmount) {
        return limitService.paymentProcess(userId, paymentAmount);
    }
}
