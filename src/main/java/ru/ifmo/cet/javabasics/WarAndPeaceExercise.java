package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class WarAndPeaceExercise {
    private static StringBuilder vault = new StringBuilder();

    static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        readAndPutInDictionary(tome12Path.toFile());
        readAndPutInDictionary(tome34Path.toFile());

        return writeToFile();
    }

    private static void readAndPutInDictionary(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                bufferedReader.lines().forEach(a -> vault.append(" ").append(a));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String writeToFile() {
        Map<String, Integer> dictionary =
                Arrays.stream(vault.toString()
                        .split("[^а-яА-Яa-zA-Z]"))
                        .filter(a -> a.length() > 3)
                        .map(a -> new Words(a.toLowerCase(), 1))
                        .collect(Collectors.groupingBy(Words::getWord, Collectors.summingInt(Words::getAmount)));

        return  dictionary.entrySet().stream()
                .filter(a -> a.getValue() > 9)
                .map((a) -> new Words(a.getKey(), a.getValue()))
                .sorted()
                .map(Words::toString)
                .collect(Collectors.joining())
                .trim();
    }
}