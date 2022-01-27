package com.github.chandanv89.dictionaryservice.schedulers;

import com.github.chandanv89.dictionaryservice.model.DictionaryResponse;
import com.github.chandanv89.dictionaryservice.model.GlobalValues;
import com.github.chandanv89.dictionaryservice.services.DictionaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
public class DailyScheduler {
    private final GlobalValues globalValues = GlobalValues.getInstance();

    private final DictionaryService dictionaryService;

    @EventListener(ApplicationReadyEvent.class) // run initially when the application starts
    @Scheduled(cron = "0 0 0 * * *") // then, once everyday
    public void getWordOfTheDay() {
        DictionaryResponse response = dictionaryService.getRandomWord(null);
        globalValues.setWordOfTheDay(response.getWords().get(0));

        log.info("Word of the day: {}", globalValues.getWordOfTheDay().getWord());
    }
}
