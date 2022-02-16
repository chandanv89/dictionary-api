package com.github.chandanv89.dictionary.api.schedulers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chandanv89.dictionary.api.environment.Config;
import com.github.chandanv89.dictionary.api.model.Word;
import com.github.chandanv89.dictionary.api.repository.DictionaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
        Map<String, String> dictionary;

        try {
            dictionary = mapper.readValue(new ClassPathResource(config.getJpa().getSeedPath()).getInputStream(), typeRef);

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
        } catch (Exception e) {
            log.error("Something went wrong!", e);
        }

        long end = System.currentTimeMillis();
        log.info("Dictionary populated with {} words in {} ms", words.size(), (end - start));
    }
}
