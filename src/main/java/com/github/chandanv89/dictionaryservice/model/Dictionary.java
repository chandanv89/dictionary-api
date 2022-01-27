package com.github.chandanv89.dictionaryservice.model;

import lombok.Data;

import java.util.Map;

@Data
public class Dictionary {
    private Map<String, String> words;
}
