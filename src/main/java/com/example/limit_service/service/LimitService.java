package com.example.limit_service.service;

import org.springframework.http.ResponseEntity;

public interface LimitService {

    /**
     * Произвести оплату
     *
     * @param userId        - идентификатор пользователя
     * @param paymentAmount - сумма платежа
     * @return статус ответа в зависимости от успешности операции
     */
    ResponseEntity<String> paymentProcess(long userId, long paymentAmount);

    /**
     * Валидация лимитов пользователя
     * @param userId        - идентификатор пользователя
     * @param paymentAmount - сумма платежа
     * @return статус ответа в зависимости от успешности операции
     */
    void validateLimit(long userId, long paymentAmount);

}
