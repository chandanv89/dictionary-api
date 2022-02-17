package com.github.chandanv89.dictionary.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "word")
@AllArgsConstructor
@NoArgsConstructor
public class Word implements Serializable, Comparable<Word> {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "word", unique = true, length = 500)
    private String word;

    @Column(name = "meaning", length = 25000)
    private String meaning;

    @Column(name = "word_length")
    private int wordLength;

    public Word(String word, String meaning, int wordLength) {
        this.word = word;
        this.meaning = meaning;
        this.wordLength = wordLength;
    }

    @Override
    public int compareTo(Word word) {
        return this.word.compareTo(word.word);
    }
}
