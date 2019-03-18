package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");


        List<String> tome12 = Files.readAllLines(tome12Path, Charset.forName("windows-1251"));
        List<String> tome34 = Files.readAllLines(tome34Path, Charset.forName("windows-1251"));
        List<String> tome1234 = new ArrayList<>();
        tome1234.addAll(tome12);
        tome1234.addAll(tome34);


               String result = tome1234.stream()
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^а-яa-zё]", " "))
                .map(s -> s.split("\\s"))
                .flatMap(s -> Arrays.stream(s))
                .filter((s) -> s.length() >= 4)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s -> s.getValue() >= 10)
                .sorted(new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o1.getValue().compareTo(o2.getValue()) == 0
                                ? o1.getKey().compareTo(o2.getKey())
                                : o1.getValue().compareTo(o2.getValue()) * (-1);
                    }
                }).map(s -> s.getKey() + " - " +s.getValue()).reduce("", (x, y) -> x + y + "\n").trim();





        return result;
    }
}