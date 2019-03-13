package ru.ifmo.cet.javabasics;

import com.google.common.collect.ComparisonChain;
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
        CombinedTextFileStream combinedTextFileStream =
                new CombinedTextFileStream(Charset.forName("windows-1251"), tome12Path, tome34Path);

        computeFrequency(wordFrequency, combinedTextFileStream);

        StringBuilder result = buildFrequencyString(wordFrequency);

        return result.toString().trim();
    }

    private static StringBuilder buildFrequencyString(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
        StringBuilder result = new StringBuilder();

        try (Stream<Map.Entry<String, Integer>> stream = sortedList.stream()) {
            stream
                    .filter(entry -> entry.getValue() >= MIN_FREQUENCY)
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                            .reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .forEach(entry -> result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n"));
        }
        
        return result;
    }

    private static void computeFrequency(Map<String, Integer> wordFrequency, CombinedTextFileStream combinedTextFileStream) {
        try (Stream<String> stream = combinedTextFileStream.getCombinedStream()) {
            stream
                    .flatMap(line -> Arrays.stream(line.split("[^a-zA-Zа-яА-Я]")))
                    .filter(word -> word.length() >= MIN_LENGTH)
                    .map(String::toLowerCase)
                    .forEach(word -> wordFrequency.merge(word, 1, Integer::sum));
        }
    }
}