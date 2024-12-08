package com.example.limit_service.scheduler;

import com.example.limit_service.persistence.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LimitResetScheduler {

    private final LimitRepository limitRepository;

    @Scheduled(cron = "0 0 0 * * ?", zone = "Europe/Moscow")
    public void resetLimits() {
        log.info("Сброс лимитов пользователей к стандартному значению");
        limitRepository.resetLimit();
    }
}