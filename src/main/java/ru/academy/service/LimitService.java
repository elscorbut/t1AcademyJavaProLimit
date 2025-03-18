package ru.academy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.academy.config.LimitProperties;
import ru.academy.domain.Limit;
import ru.academy.dto.LimitChangeDetailsDto;
import ru.academy.dto.LimitDto;
import ru.academy.exception.IllegalLimitValueException;
import ru.academy.exception.IncorrectLimitRestoreValueException;
import ru.academy.exception.LimitExceededException;
import ru.academy.exception.UserNotFoundException;
import ru.academy.mapper.LimitMapper;
import ru.academy.repository.LimitRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class LimitService {

    private final LimitRepository limitRepository;
    private final LimitMapper limitMapper;
    private final LimitProperties limitProperties;

    @Transactional
    public LimitDto decreaseLimit(LimitChangeDetailsDto limitChangeDetails) {
        validateLimitChange(limitChangeDetails.getLimitChange());
        Limit limit = limitRepository.findByUserId(limitChangeDetails.getUserId()).
                orElseGet(() -> limitRepository.save(new Limit(limitChangeDetails.getUserId(), limitProperties.getDefaultValue())));
        double resultLimit = limit.getValue() - limitChangeDetails.getLimitChange();
        if (resultLimit < 0) {
            throw new LimitExceededException("The limit is exceeded when making the payment");
        }
        limit.setValue(resultLimit);
        limitRepository.save(limit);

        return limitMapper.mapToDto(limit);
    }

    @Transactional
    public LimitDto restoreLimit(LimitChangeDetailsDto limitChangeDetails) {
        validateLimitChange(limitChangeDetails.getLimitChange());
        Limit limit = limitRepository.findByUserId(limitChangeDetails.getUserId()).
                orElseThrow(() -> new UserNotFoundException("User with id " + limitChangeDetails.getUserId() + " not found"));
        double resultLimit = limit.getValue() + limitChangeDetails.getLimitChange();
        if (resultLimit > limitProperties.getDefaultValue()) {
            throw new IncorrectLimitRestoreValueException("Incorrect value when restoring the limit");
        }
        limit.setValue(resultLimit);
        limitRepository.save(limit);

        return limitMapper.mapToDto(limit);
    }

    @Transactional
    public void resetAllLimits() {
        log.info("Starting limit reset for all users...");
        limitRepository.resetAllLimits(limitProperties.getDefaultValue());
        log.info("Limit reset completed.");
    }

    private void validateLimitChange(Double limitChange) {
        if (limitChange == null || limitChange < 0) {
            throw new IllegalLimitValueException("The limit change value must be greater than zero");
        }
    }
}
