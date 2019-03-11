package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO fI word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order fi needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //throw new UnsupportedOperationException();

        Map<String, Integer> wordStatistics = countWords(tome12Path, tome34Path);
        wordStatistics.entrySet().removeIf(
                entry -> entry.getKey().length() < 4 || entry.getValue() < 10);

        Map<String, Integer> sortedWordStatistics = sortByValue(wordStatistics);
        StringBuilder stringBuilder = new StringBuilder();
        sortedWordStatistics.forEach( (key, value) -> stringBuilder.append(key + " - " + value + "\n") );
        stringBuilder.setLength(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static Map<String, Integer> countWords(Path tome12Path, Path tome34Path){
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern pattern = Pattern.compile("[а-яa-z]+");

        List<String> wordsFirstPart = new ArrayList<>();
        List<String> wordsSecondPart = new ArrayList<>();

        try(Stream<String> lines = Files.lines(tome12Path, Charset.forName("Windows-1251"))) {
            List<String> linesLowerCase = lines
                    .map(line -> line.toLowerCase())
                    .collect(Collectors.toList());

            linesLowerCase.stream()
                    .flatMap(x -> Arrays.stream(x.split("[^a-zа-я]")))
                    .map(pattern::matcher)
                    .filter(Matcher::matches)
                    .forEach(matcher -> wordsFirstPart.add(matcher.group()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Stream<String> lines = Files.lines(tome34Path, Charset.forName("Windows-1251"))) {
            List<String> linesLowerCase = lines
                    .map(line -> line.toLowerCase())
                    .collect(Collectors.toList());

            linesLowerCase.stream()
                    .flatMap(x -> Arrays.stream(x.split("[^a-zа-я]")))
                    .map(pattern::matcher)
                    .filter(Matcher::matches)
                    .forEach(matcher -> wordsSecondPart.add(matcher.group()));
        } catch (IOException e) {
            e.printStackTrace();
        }

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