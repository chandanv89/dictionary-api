package com.github.chandanv89.dictionary.api.filters;

import com.github.chandanv89.dictionary.api.model.Filter;
import com.github.chandanv89.dictionary.api.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WordEndsWithFilter implements WordFilter<List<Word>> {

    @Override
    public List<Word> applyFilter(List<Word> words, Filter predicate) {
        String endsWith = predicate.getEndsWith();

        if (endsWith != null && !endsWith.trim().equals("")) {
            int initialLength = words.size();
            words = words.stream()
                    .filter(w -> w.getWord().toLowerCase().endsWith(endsWith.toLowerCase()))
                    .collect(Collectors.toList());

            log.debug("Predicate: endsWith({}); Initial Count: {}; Filtered Count: {}", endsWith, initialLength, words.size());
        }

        return words;
    }

    @Override
    public String toString() {
        return "WordEndsWithFilter";
    }
}
