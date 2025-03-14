package ru.academy.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academy.service.LimitService;

@Slf4j
@RequiredArgsConstructor
@Component
public class LimitResetScheduler {

    private final LimitService limitService;

    @Scheduled(cron = "${limit.reset-cron}")
    public void resetAllLimits() {
        log.info("Starting limit reset for all users...");
        limitService.resetAllLimits();
        log.info("Limit reset completed.");
    }
}
