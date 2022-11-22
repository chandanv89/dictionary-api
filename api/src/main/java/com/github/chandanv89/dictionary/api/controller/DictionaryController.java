package com.github.chandanv89.dictionary.api.controller;

import com.github.chandanv89.dictionary.api.model.DictionaryResponse;
import com.github.chandanv89.dictionary.api.model.Filter;
import com.github.chandanv89.dictionary.api.services.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:9091", "https://*.herokuapp.com"})
@RequestMapping("/rest/v1/dictionary")
public class DictionaryController {
    private final DictionaryService service;

    @Operation(summary = "Gets a pagenated list of words.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DictionaryResponse.class))})})
    @GetMapping("/words")
    public DictionaryResponse getWords(@RequestParam(defaultValue = "word") String sortBy,
                                       @RequestParam(defaultValue = "ASC") String sortDir,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "0") Integer currentPage) {
        return service.getWords(sortBy, sortDir, pageSize, currentPage);
    }

    @Operation(summary = "Gets a single word and it's definition.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the word",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DictionaryResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DictionaryResponse.class))})
    })
    @GetMapping("/words/{word}")
    public DictionaryResponse getMeaningFor(@PathVariable String word) {
        return service.getMeaningFor(word);
    }

    @Operation(summary = "Gets a random word and it's definition.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DictionaryResponse.class))})})
    @GetMapping("/random-word")
    public DictionaryResponse getRandomWord(@RequestBody(required = false) Filter filter) {
        return service.getRandomWord(filter);
    }


    @Operation(summary = "Gets the word of the day.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the word",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DictionaryResponse.class))})
    })
    @GetMapping("/word-of-the-day")
    public DictionaryResponse getWordOfTheDay() {
        return service.getWordOfTheDay();
    }
}
