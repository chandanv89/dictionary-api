package com.github.chandanv89.dictionary.api.environment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ComponentScan(basePackages = {"com.github.chandanv89.dictionaryservice.aop"})
@ConfigurationProperties(prefix = "dictionary.service")
public class Config {
    private JpaConfigs jpa;
    private SchedulerConfigs scheduler;
}
