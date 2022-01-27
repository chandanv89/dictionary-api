package com.github.chandanv89.dictionaryservice.schedulers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chandanv89.dictionaryservice.environment.Config;
import com.github.chandanv89.dictionaryservice.model.Word;
import com.github.chandanv89.dictionaryservice.repository.DictionaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DictionaryLoader {
    private final DictionaryRepository repository;
    private final Config config;

    private final TypeReference<Map<String, String>> typeRef = new TypeReference<>() {
        @Override
        public Type getType() {
            return super.getType();
        }
    };

    private ObjectMapper mapper;

    @PostConstruct
    @Transactional()
    public void loadWords() {
        long start = System.currentTimeMillis();
        mapper = new ObjectMapper();
        List<Word> words = new ArrayList<>();
        Map<String, String> dictionary = new HashMap<>();

        try {
            dictionary = mapper.readValue(new ClassPathResource(config.getJpa().getSeedPath()).getFile(), typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : dictionary.keySet()) {
            words.add(new Word(word, dictionary.get(word), word.length()));
        }

        List<Word> batch = new ArrayList<>();
        words = words.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < words.size(); i++) {
            batch.add(words.get(i));

            if (i == words.size() - 1 || i % config.getJpa().getBatchSize() == 0) {
                repository.saveAll(batch);
                batch = new ArrayList<>();
            }
        }

        long end = System.currentTimeMillis();
        log.info("Dictionary populated with {} words in {} ms", words.size(), (end - start));

    }
}