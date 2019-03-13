package ru.ifmo.cet.javabasics;

import com.google.common.collect.ComparisonChain;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        final StringBuilder stringBuilder = new StringBuilder();

        final List<Map.Entry<String, Long>> entries;
        try {
            entries = Stream.concat(
                    Files.lines(tome12Path, Charset.forName("cp1251")),
                    Files.lines(tome34Path, Charset.forName("cp1251")))
                    .map(x -> x.toLowerCase())
                    .flatMap(s -> Arrays.stream(s.split("[^\\p{L}]")))
                    .filter(s -> s.length() >= 4)
                    .collect(
                            Collectors.groupingBy(x -> x, Collectors.counting()))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() >= 10)
                    .collect(Collectors.toList());

            entries.sort((o1, o2) -> ComparisonChain.start()
                    .compare(o2.getValue(), o1.getValue())
                    .compare(o1.getKey(), o2.getKey())
                    .result());

            entries.stream()
                    .forEach(entry -> stringBuilder
                            .append(entry.getKey())
                            .append(" - ")
                            .append(entry.getValue())
                            .append("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString().trim();
    }


}