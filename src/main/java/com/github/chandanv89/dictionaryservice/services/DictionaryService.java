package com.github.chandanv89.dictionaryservice.services;

import com.github.chandanv89.dictionaryservice.filters.FilterProcessor;
import com.github.chandanv89.dictionaryservice.model.DictionaryResponse;
import com.github.chandanv89.dictionaryservice.model.Filter;
import com.github.chandanv89.dictionaryservice.model.GlobalValues;
import com.github.chandanv89.dictionaryservice.model.Word;
import com.github.chandanv89.dictionaryservice.repository.DictionaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class DictionaryService {
    public final GlobalValues globalValues = GlobalValues.getInstance();
    public static final int DEFAULT_PAGE_SIZE = 10;

    private final DictionaryRepository repository;
    private final FilterProcessor filterProcessor;

    public DictionaryResponse getWords(String sortBy,
                                       String sortDir,
                                       Integer pageSize,
                                       Integer currentPage) {


        PageRequest pageRequest = PageRequest
                .of(currentPage, pageSize, Sort.Direction.fromString(sortDir), sortBy);

        Page<Word> response = repository.findAll(pageRequest);

        return DictionaryResponse.builder()
                .words(response.toList())
                .totalResponseSize(response.getTotalElements())
                .build();
    }

    public DictionaryResponse getMeaningFor(String word) {

        Page<Word> response = repository.findByWordIgnoreCase(word, Pageable.ofSize(DEFAULT_PAGE_SIZE));

        return DictionaryResponse.builder()
                .words(response.getContent())
                .totalResponseSize(response.getTotalElements())
                .build();
    }

    public DictionaryResponse getRandomWord(Filter filter) {
        List<Word> words = repository.findAll();

        words = filterProcessor.processFilters(words, filter);

        log.debug("Filtered words count: {}", words.size());

        if (words.size() < 1) return DictionaryResponse.builder().build();

        Random random = new Random();

        return DictionaryResponse.builder()
                .words(Collections.singletonList(words.get(random.nextInt(words.size()))))
                .totalResponseSize(1L)
                .build();
    }

    public DictionaryResponse getWordOfTheDay() {
        return DictionaryResponse.builder()
                .words(Collections.singletonList(globalValues.getWordOfTheDay()))
                .totalResponseSize(1L)
                .build();
    }
}
