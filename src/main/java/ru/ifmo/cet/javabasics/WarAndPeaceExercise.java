package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        
        final String CHARSET = "windows-1251";
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        final List<String> fullTome = new ArrayList<>();

        fullTome.addAll(Files.readAllLines(tome12Path, Charset.forName(CHARSET)));
        fullTome.addAll(Files.readAllLines(tome34Path, Charset.forName(CHARSET)));

        return fullTome.stream()
                .map(s -> s.split("[^a-zA-Zа-яА-ЯёЁ]"))
                .flatMap(Arrays::stream)
                .filter(s -> s.length() >= 4)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s -> s.getValue() >= 10)
                .sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()) != 0
                        ? -entry1.getValue().compareTo(entry2.getValue()) :
                        entry1.getKey().compareTo(entry2.getKey()))
                .map(s -> s.getKey() + " - " + s.getValue()).reduce("", (x, y) -> x + y + "\n").trim();
    }

}
