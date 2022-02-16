package com.github.chandanv89.dictionary.api.environment;

import lombok.Data;

@Data
public class JpaConfigs {
    private int batchSize;
    private String seedPath;
}
