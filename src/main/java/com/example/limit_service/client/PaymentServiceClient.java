package com.example.limit_service.client;

import com.example.limit_service.exception.InsufficientFundsException;
import com.example.limit_service.exception.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceClient {

    public ResponseEntity<String> paymentProcess(long userId, long paymentAmount) {
        log.debug("#paymentProcess: userId = {}, paymentAmount = {}", userId, paymentAmount);

        if (Objects.requireNonNull(findUserById(userId)).balance < paymentAmount) {
            throw new InsufficientFundsException("Недостаточно средств на балансе");
        }

        changeBalance(userId, paymentAmount);

        return ResponseEntity.ok().body("Платеж успешно выполнен");
    }

    private Users findUserById(long userId) {

        for (Users user : Users.values()) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        throw new NotFoundException("Пользователь не найден");
    }

    private void changeBalance(long userId, long paymentAmount) {
        findUserById(userId).balance -= paymentAmount;
    }

    private enum Users {
        USER_1(1, 9000),
        USER_2(2, 400),
        USER_3(3, 10000),
        USER_4(4, 5233),
        USER_5(5, 11002);

        @Getter
        long userId;
        long balance;

        Users(long userId, long balance) {
            this.userId = userId;
            this.balance = balance;
        }
    }

    public long findBalanceByUserId(long userId) {
        return findUserById(userId).balance;
    }
}
