package com.github.chandanv89.dictionaryservice.repository;

import com.github.chandanv89.dictionaryservice.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Word, Long> {
    Page<Word> findByWordIgnoreCase(String word, Pageable pageable);

    Page<Word> findByWordLength(Integer wordLength, Pageable pageable);
}
