package com.github.chandanv89.dictionaryservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DictionaryResponse {
    private List<Word> words;
    private Long totalResponseSize;
}
