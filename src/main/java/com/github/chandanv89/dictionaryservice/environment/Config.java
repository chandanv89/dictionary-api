package com.github.chandanv89.dictionaryservice.environment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "dictionary.service")
public class Config {
    private JpaConfigs jpa;
    private SchedulerConfigs scheduler;
}
