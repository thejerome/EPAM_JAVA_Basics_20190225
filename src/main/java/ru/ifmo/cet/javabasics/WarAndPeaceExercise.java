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

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        try{
            return Stream.concat(
                    Files.lines(tome12Path, Charset.forName("Windows-1251")),
                    Files.lines(tome34Path, Charset.forName("Windows-1251")))
                    .parallel()
                    .flatMap(s-> Arrays.stream(s.split("[^a-zA-Zа-яА-Я]")))
                    .filter(s -> (s.length() > 3))
                    .map(String::toLowerCase)
                    .collect(groupingBy(Function.identity(), summingInt(e -> 1)))
                    .entrySet()
                    .parallelStream()
                    .filter(e -> (e.getValue()>9))
                    .sorted((o1, o2) -> // to much ternary, but test isn't not loyal to ifstatement
                            o1.getValue()<o2.getValue()
                            ? 1
                            : o1.getValue()>o2.getValue()
                                ? -1
                                : o1.getKey().compareTo(o2.getKey()))
                    .map(e -> e.getKey()+" - "+e.getValue())
                    .collect(Collectors.joining("\n","",""));
        } catch (IOException ex) {
            throw new UnsupportedOperationException("Exception!");
        }
    }
}