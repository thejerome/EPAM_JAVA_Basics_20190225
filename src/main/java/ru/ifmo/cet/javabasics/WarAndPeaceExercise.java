package ru.ifmo.cet.javabasics;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {
    private static String result;
    private static WarAndPeaceExercise warAndPeaceExercise = new WarAndPeaceExercise();
    private static Map<String, Long> wordMap;

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        warAndPeaceExercise.readAndParseWAP(tome12Path, tome34Path);
        warAndPeaceExercise.makingResult(entriesSortedByValues(wordMap));
        return result;
    }

    public void readAndParseWAP(Path tome1, Path tome2) {

        try {
            Stream<String> wordStream12 = Files.lines(tome1, Charset.forName("CP1251"));
            Stream<String> wordStream34 = Files.lines(tome2, Charset.forName("CP1251"));
            Stream<String> wordStreamAll = Stream.concat(wordStream12, wordStream34);

        wordMap = wordStreamAll
                .map(String::toLowerCase)
                .flatMap(d -> Arrays.stream(d
                        .split("[^A-Za-zА-Яа-я]"))
                        .filter(s -> s.length() >= 4))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        wordMap = wordMap.entrySet().stream()
                .filter(s -> s.getValue() >= 10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        wordStream12.close();
        wordStream34.close();
        wordStreamAll.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makingResult(SortedSet<Map.Entry<String,Long>> entrySortedSet) {
        Formatter formatter = new Formatter();
        entrySortedSet.forEach(s -> formatter.format("%1$s - %2$d\n", s.getKey(), s.getValue()));
        result = formatter.toString().trim();
    }

    private static SortedSet<Map.Entry<String,Long>> entriesSortedByValues(Map<String,Long> map) {
        SortedSet<Map.Entry<String,Long>> sortedEntries = new TreeSet<>(
                (e1, e2) -> {
                    int res = -e1.getValue().compareTo(e2.getValue());
                    res = (res == 0) ? e1.getKey().compareTo(e2.getKey()) : res;
                    return res;
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}