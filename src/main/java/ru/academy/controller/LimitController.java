package ru.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.academy.dto.LimitChangeDetailsDto;
import ru.academy.dto.LimitDto;
import ru.academy.service.LimitService;

@RequiredArgsConstructor
@RequestMapping("/api/v1/limits")
@RestController
public class LimitController {

    private final LimitService limitService;

    @PostMapping("/decrease")
    public LimitDto decreaseLimit(@RequestBody LimitChangeDetailsDto limitChangeDetails) {
        return limitService.decreaseLimit(limitChangeDetails);
    }

    @PostMapping("/restore")
    public LimitDto restoreLimit(@RequestBody LimitChangeDetailsDto limitChangeDetails) {
        return limitService.restoreLimit(limitChangeDetails);
    }

    @PostMapping("/default")
    public void setDefaultLimit(@RequestParam Double defaultLimit) {
        limitService.setDefaultLimit(defaultLimit);
    }
}
