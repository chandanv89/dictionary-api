package com.github.chandanv89.dictionary.api.filters;

import com.github.chandanv89.dictionary.api.model.Filter;
import com.github.chandanv89.dictionary.api.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WordContainsFilter implements WordFilter<List<Word>> {

    @Override
    public List<Word> applyFilter(List<Word> words, Filter predicate) {
        String contains = predicate.getContains();

        if (contains != null && !contains.trim().equals("")) {
            int initialLength = words.size();
            words = words.stream()
                    .filter(w -> w.getWord().toLowerCase().contains(contains.toLowerCase()))
                    .collect(Collectors.toList());

            log.debug("Predicate: contains({}); Initial Count: {}; Filtered Count: {}", contains, initialLength, words.size());
        }

        return words;
    }

    @Override
    public String toString() {
        return "WordContainsFilter";
    }
}
