package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {
    private static final int MIN_WORD_LENGTH = 4;
    private static final int MIN_FREQUENCY = 10;

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        List<String> lines = Files.readAllLines(tome12Path, Charset.forName("windows-1251"));
        lines.addAll(Files.readAllLines(tome34Path, Charset.forName("windows-1251")));

        Map<String, Integer> wordFrequency = new HashMap<>();
        Integer count;

        for (String line : lines) {
            String[] words = line.split("[^a-zA-Zа-яА-Я]");
            for (String word : words) {
                if (word.length() >= MIN_WORD_LENGTH) {
                    word = word.toLowerCase();
                    count = wordFrequency.get(word);
                    wordFrequency.put(word, count == null ? 1 : count + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
        sortedList.sort(Map.Entry.comparingByKey());
        sortedList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Map.Entry<String, Integer>> iterator = sortedList.iterator();
        Map.Entry<String, Integer> entry;

        while ((entry = iterator.next()).getValue() >= MIN_FREQUENCY) {
            stringBuilder.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        return stringBuilder.toString().trim();
    }
}