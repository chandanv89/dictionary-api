package com.github.chandanv89.dictionaryservice.filters;

import com.github.chandanv89.dictionaryservice.model.Filter;
import com.github.chandanv89.dictionaryservice.model.Word;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class FilterProcessor {
    private final List<WordFilter<List<Word>>> filters;

    public List<Word> processFilters(List<Word> words, Filter filter) {
        long start = System.currentTimeMillis();

        if (filter != null) {
            log.debug("Available Filters: {}", filters);

            for (WordFilter<List<Word>> f : filters) {
                words = f.applyFilter(words, filter);
            }
            log.info("Processed filters in {}ms", (System.currentTimeMillis() - start));
        }

        return words;
    }
}
