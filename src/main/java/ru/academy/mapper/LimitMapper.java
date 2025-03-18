package ru.academy.mapper;

import org.springframework.stereotype.Component;
import ru.academy.domain.Limit;
import ru.academy.dto.LimitDto;

@Component
public class LimitMapper {

    public LimitDto mapToDto(Limit limit) {
        return new LimitDto(
                limit.getId(),
                limit.getUserId(),
                limit.getValue()
        );
    }
}
