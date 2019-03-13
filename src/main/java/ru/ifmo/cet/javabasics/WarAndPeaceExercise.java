package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        String str = "";

        try {
            str = Stream
                    .concat(Files.lines(tome12Path, Charset.forName("windows-1251")),
                            Files.lines(tome34Path, Charset.forName("windows-1251")))
                    .flatMap(s -> Arrays.stream(s.toLowerCase().split("[^\\p{L}]")))
                    .filter(s -> s.length() >= 4)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(m -> m.getValue() >= 10)
                    .sorted((m1, m2) -> {
                        int compV = m2.getValue().compareTo(m1.getValue());
                        int compK = m1.getKey().compareTo(m2.getKey());
                        return compV != 0 ? compV : compK;
                    })
                    .map(m -> m.getKey() + " - " + m.getValue())
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }



}