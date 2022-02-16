package com.github.chandanv89.dictionary.api.filters;

import com.github.chandanv89.dictionary.api.model.Filter;
import com.github.chandanv89.dictionary.api.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WordBeginsWithFilter implements WordFilter<List<Word>> {

    @Override
    public List<Word> applyFilter(List<Word> words, Filter predicate) {
        String beginsWith = predicate.getBeginsWith();

        if (beginsWith != null && !beginsWith.trim().equals("")) {
            int initialLength = words.size();

            words = words.stream()
                    .filter(w -> w.getWord().toLowerCase().startsWith(beginsWith.toLowerCase()))
                    .collect(Collectors.toList());

            log.debug("Predicate: beginsWith({}), Initial Count: {}; Filtered Count: {}", beginsWith, initialLength, words.size());
        }

        return words;
    }

    @Override
    public String toString() {
        return "WordBeginsWithFilter";
    }
}
