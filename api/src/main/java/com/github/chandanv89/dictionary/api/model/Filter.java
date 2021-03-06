package com.github.chandanv89.dictionary.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {
    private Integer wordLength;
    private String beginsWith;
    private String endsWith;
    private String contains;
    private String notContains;
}
