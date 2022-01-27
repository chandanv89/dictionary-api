package com.github.chandanv89.dictionaryservice.environment;

import lombok.Data;

@Data
public class JpaConfigs {
    private int batchSize;
    private String seedPath;
}
