package com.github.chandanv89.dictionary.api.model;

import lombok.Getter;
import lombok.Setter;

public class GlobalValues {
    private static GlobalValues INSTANCE;

    @Getter
    @Setter
    private Word wordOfTheDay;

    private GlobalValues() {
    }

    public static GlobalValues getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalValues();
        }
        return INSTANCE;
    }
}
