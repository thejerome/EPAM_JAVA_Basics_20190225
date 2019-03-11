package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO fI word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order fi needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //throw new UnsupportedOperationException();

        String contentWAP12 = "";
        String contentWAP34 = "";
        try {
            contentWAP12 = new String(
                    Files.readAllBytes(tome12Path),
                    "Windows-1251");

            contentWAP34 = new String(
                    Files.readAllBytes(tome34Path),
                    "Windows-1251");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> wordStatistics = countWords(contentWAP12.toLowerCase(),
                contentWAP34.toLowerCase());
        wordStatistics.entrySet().removeIf(entry -> entry.getKey().length() < 4 || entry.getValue() < 10);

        Map<String, Integer> sortedWordStatistics = sortByValue(wordStatistics);
        StringBuilder stringBuilder = new StringBuilder();
        sortedWordStatistics.forEach( (key, value) -> stringBuilder.append(key + " - " + value + "\n") );
        stringBuilder.setLength(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static Map<String, Integer> countWords(String firstPart, String secondPart){
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern pattern = Pattern.compile("[а-яa-z]+");
        Matcher matcher = pattern.matcher(firstPart);

        List<String> wordsFirstPart = pattern
                .matcher(firstPart)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        List<String> wordsSecondPart = pattern
                .matcher(secondPart)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        wordsFirstPart.forEach(
                entry -> wordCount.put(entry, 0)
        );

        wordsSecondPart.forEach(
                entry -> wordCount.put(entry, 0)
        );

        wordsFirstPart.stream()
                .filter(entry -> wordCount.containsKey(entry))
                .forEach(item -> wordCount.put(item, wordCount.get(item) + 1));

        wordsSecondPart.stream()
                .filter(entry -> wordCount.containsKey(entry))
                .forEach(item -> wordCount.put(item, wordCount.get(item) + 1));

        return wordCount;
    }

    public static <String extends Comparable<? super String>,
            Integer extends Comparable<? super Integer>>
    Map<String, Integer> sortByValue(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()));

        Map<String, Integer> result = new LinkedHashMap<>();
        list.forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }
}