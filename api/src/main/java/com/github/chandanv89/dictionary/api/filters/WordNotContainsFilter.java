package com.github.chandanv89.dictionary.api.filters;

import com.github.chandanv89.dictionary.api.model.Filter;
import com.github.chandanv89.dictionary.api.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WordNotContainsFilter implements WordFilter<List<Word>> {

    @Override
    public List<Word> applyFilter(List<Word> words, Filter predicate) {
        String notContains = predicate.getNotContains();

        if (notContains != null && !notContains.trim().equals("")) {
            int initialLength = words.size();
            words = words.stream()
                    .filter(w -> !w.getWord().toLowerCase().contains(notContains.toLowerCase()))
                    .collect(Collectors.toList());

            log.debug("Predicate: notContains({}); Initial Count: {}; Filtered Count: {}", notContains, initialLength, words.size());
        }

        return words;
    }

    @Override
    public String toString() {
        return "WordNotContainsFilter";
    }
}
