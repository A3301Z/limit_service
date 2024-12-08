package com.example.limit_service.service.impl;

import com.example.limit_service.client.PaymentServiceClient;
import com.example.limit_service.exception.NotFoundException;
import com.example.limit_service.exception.ReachedLimitException;
import com.example.limit_service.persistence.entity.Limit;
import com.example.limit_service.persistence.repository.LimitRepository;
import com.example.limit_service.service.LimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final PaymentServiceClient paymentServiceClient;
    private final LimitRepository limitRepository;

    @Transactional
    public ResponseEntity<String> paymentProcess(long userId, long paymentAmount) {
        log.debug("#paymentProcess: userId = {}, paymentAmount = {}", userId, paymentAmount);

        Limit dayLimit = limitRepository.checkLimit(userId).orElseThrow(
                () -> new NotFoundException("Пользователь не найден")
        );

        validateLimit(userId, paymentAmount);

        paymentServiceClient.paymentProcess(userId, paymentAmount);
        limitRepository.updateLimit(userId, paymentAmount);


        return ResponseEntity.ok().body("Платеж успешно выполнен.\nВаш лимит на сегодня составляет: "
                + (dayLimit.getDayLimit() - paymentAmount) + "\nБаланс: " + paymentServiceClient.findBalanceByUserId(userId));
    }

    public void validateLimit(long userId, long paymentAmount) {
        log.debug("#validateLimit: userId = {}, paymentAmount = {}", userId, paymentAmount);

        if (limitRepository.checkLimit(userId).get().getDayLimit() == 0) {
            log.info("Лимит на сегодня исчерпан. Лимит будет восстановлен в 00:00 по МСК");
            throw new ReachedLimitException("Лимит на сегодня исчерпан. Лимит будет восстановлен в 00:00 по МСК");
        }

        if (limitRepository.checkLimit(userId).get().getDayLimit() < paymentAmount) {
            log.info("Сумма платежа выходит за рамки дневного лимита");
            throw new ReachedLimitException("Сумма платежа выходит за рамки дневного лимита");
        }
    }
}
