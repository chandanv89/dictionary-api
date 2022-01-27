package com.github.chandanv89.dictionaryservice.controller;

import com.github.chandanv89.dictionaryservice.services.DictionaryService;
import com.github.chandanv89.dictionaryservice.model.DictionaryResponse;
import com.github.chandanv89.dictionaryservice.model.Filter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/rest/v1/dictionary")
public class DictionaryController {
    private final DictionaryService service;

    @GetMapping("/words")
    public DictionaryResponse getWords(@RequestParam(defaultValue = "word") String sortBy,
                                       @RequestParam(defaultValue = "ASC") String sortDir,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "0") Integer currentPage) {
        return service.getWords(sortBy, sortDir, pageSize, currentPage);
    }

    @GetMapping("/words/{word}")
    public DictionaryResponse getMeaningFor(@PathVariable String word) {
        return service.getMeaningFor(word);
    }

    @GetMapping("/random-word")
    public DictionaryResponse getRandomWord(@RequestBody(required = false) Filter filter) {
        return service.getRandomWord(filter);
    }

    @GetMapping("/word-of-the-day")
    public DictionaryResponse getWordOfTheDay() {
        return service.getWordOfTheDay();
    }
}
