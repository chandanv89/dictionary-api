package com.github.chandanv89.dictionaryservice.filters;

import com.github.chandanv89.dictionaryservice.model.Filter;
import com.github.chandanv89.dictionaryservice.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WordLengthFilter implements WordFilter<List<Word>> {

    @Override
    public List<Word> applyFilter(List<Word> words, Filter predicate) {
        int wordLength;
        try {
            wordLength = predicate.getWordLength();
        } catch (Exception e) {
            wordLength = 0;
        }

        if (wordLength > 0) {
            int initialLength = words.size();
            words = words.stream().filter(word -> word.getWordLength() == predicate.getWordLength())
                    .collect(Collectors.toList());

            log.debug("Predicate: wordLength({}); Initial Count: {}; Filtered Count: {}", wordLength, initialLength, words.size());
        }

        return words;
    }

    @Override
    public String toString() {
        return "WordLengthFilter";
    }
}
