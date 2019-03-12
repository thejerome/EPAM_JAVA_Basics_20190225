package ru.ifmo.cet.javabasics;

import com.google.common.collect.Streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    private static final int MIN_LENGTH = 4;
    private static final int MIN_FREQUENCY = 10;

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        Map<String, Integer> wordFrequency = new HashMap<>();

        try (Stream<String> stream = Streams.concat(
                Files.lines(tome12Path, Charset.forName("windows-1251")),
                Files.lines(tome34Path, Charset.forName("windows-1251"))
        )) {
            stream
                    .flatMap(line -> Arrays.stream(line.split("[^a-zA-Zа-яА-Я]")))
                    .filter(word -> word.length() >= MIN_LENGTH)
                    .map(String::toLowerCase)
                    .forEach(word -> wordFrequency.put(word, wordFrequency.get(word) == null ? 1 : wordFrequency.get(word) + 1));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
        StringBuilder result = new StringBuilder();

        sortedList.stream()
                .filter(entry -> entry.getValue() >= MIN_FREQUENCY)
                .sorted(Map.Entry.comparingByKey())
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n"));

        return result.toString().trim();
    }
}