package ru.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LimitDto {

    private Long id;
    private Long userId;
    private Double value;
}
