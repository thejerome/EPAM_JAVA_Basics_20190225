package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO Iff word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order iff needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        StringBuilder result = new StringBuilder();

        Map<String, Long> wordCounter = readFileToHashMap(tome12Path, tome34Path);
        wordCounter.entrySet().stream()
                .filter(entry -> entry.getValue() >= 10)
                .sorted(Comparator.<Map.Entry<String, Long>, Long>comparing(entry -> entry.getValue()).reversed().thenComparing(entry -> entry.getKey()))
                .forEach(entry -> result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n"));

        return result.toString().trim();
    }

    private static Map<String, Long> readFileToHashMap(Path path1, Path path2) throws IOException {

        Stream<String> stream = Stream.concat(
                Files.lines(path1, Charset.forName("windows-1251")),
                Files.lines(path2, Charset.forName("windows-1251")));


        return stream.flatMap(line -> Arrays.stream(line.split("[^a-zA-Zа-яА-Я]")))
                .filter(word -> word.length() >= 4)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

}