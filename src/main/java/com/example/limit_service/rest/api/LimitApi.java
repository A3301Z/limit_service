package com.example.limit_service.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/limit")
@Tag(name = "Api лимитов на платежи")
public interface LimitApi {

    @GetMapping("/pay")
    @Operation(description = "Произвести оплату")
    ResponseEntity<String> paymentProcess(@RequestParam long userId,
                                          @RequestParam long paymentAmount);
}
