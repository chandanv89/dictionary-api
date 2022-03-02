package com.github.chandanv89.dictionary.api.model;

import lombok.Data;

@Data
public class Options {
    private String sortBy = "word";
    private String sortDirection = "ASC";
    private Integer page = 0;
    private Integer pageSize = 10;

    private Integer wordLength;
    private String beginsWith;
    private String endsWith;
    private String contains;
    private String notContains;
}
