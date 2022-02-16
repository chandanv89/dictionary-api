package com.github.chandanv89.dictionary.api.schedulers;

import com.github.chandanv89.dictionary.api.model.DictionaryResponse;
import com.github.chandanv89.dictionary.api.model.GlobalValues;
import com.github.chandanv89.dictionary.api.services.DictionaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
public class DailyScheduler {
    private static final long H24_IN_MILLIS = 24 * 60 * 60 * 1000;
    private final GlobalValues globalValues = GlobalValues.getInstance();

    private final DictionaryService dictionaryService;

    @EventListener(ApplicationReadyEvent.class) // run initially when the application starts
    @Scheduled(cron = "0 0 0 * * *") // then, once everyday
    public void getWordOfTheDay() {
        getWotd();
        log.info("Word of the day: {}", globalValues.getWordOfTheDay().getWord());
    }

    private void getWotd() {
        try {
            Path dataFilePath = Path.of("./wotd.data");

            if (dataFilePath.toFile().exists()) {
                List<String> lines = Files.readAllLines(dataFilePath);
                log.debug("Found {}: {}", dataFilePath, lines);
                long lastWritten = Long.parseLong(lines.get(0));

                if (Instant.now().minus(lastWritten, ChronoUnit.MILLIS).toEpochMilli() > H24_IN_MILLIS || isMidnight()) {
                    writeNewWotdToFile(dataFilePath);
                } else {
                    readWotdFromFile(dataFilePath);
                    log.info("Successfully restored the WOTD from file");
                }
            } else if (dataFilePath.toFile().createNewFile()) {
                writeNewWotdToFile(dataFilePath);
            }
        } catch (IOException e) {
            log.error("Something went wrong", e);
        }
    }

    private boolean isMidnight() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 0;
    }

    private void writeNewWotdToFile(Path path) throws IOException {
        DictionaryResponse response = dictionaryService.getRandomWord(null);
        globalValues.setWordOfTheDay(response.getWords().get(0));

        String data = String.valueOf(System.currentTimeMillis()) + '\n' + globalValues.getWordOfTheDay().getWord();

        log.info("Writing {}: {}", path, globalValues.getWordOfTheDay().getWord());
        Files.writeString(path, data);
    }

    private void readWotdFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        String word = lines.get(1);
        DictionaryResponse response = dictionaryService.getMeaningFor(word);
        globalValues.setWordOfTheDay(response.getWords().get(0));
    }
}
