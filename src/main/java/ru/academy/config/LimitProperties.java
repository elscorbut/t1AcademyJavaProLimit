package ru.academy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "limit")
public class LimitProperties {

    private Double defaultValue;
}
