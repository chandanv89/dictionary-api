package com.github.chandanv89.dictionary.api.services;

import com.github.chandanv89.dictionary.api.filters.FilterProcessor;
import com.github.chandanv89.dictionary.api.model.*;
import com.github.chandanv89.dictionary.api.repository.DictionaryRepository;
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

    public DictionaryResponse getWords(Options options) {


        PageRequest pageRequest = PageRequest.of(
                options.getPage(),
                options.getPageSize(),
                Sort.Direction.fromString(options.getSortDirection()),
                options.getSortBy());

        List<Word> words = repository.findAll();

        Filter filter = Filter.builder().beginsWith(options.getBeginsWith())
                .contains(options.getContains())
                .endsWith(options.getEndsWith())
                .wordLength(options.getWordLength())
                .notContains(options.getNotContains()).build();

        words = filterProcessor.processFilters(words, filter);

        Page<Word> response = repository.findAll(pageRequest);

        return DictionaryResponse.builder()
                .words(response.toList())
                .totalResponseSize(response.getTotalElements())
                .totalPages(response.getTotalPages())
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
