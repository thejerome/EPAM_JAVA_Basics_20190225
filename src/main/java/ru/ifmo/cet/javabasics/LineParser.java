package ru.ifmo.cet.javabasics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineParser {
    public List<String> parseLine (String line) {
        return Stream.of (line).
                flatMap (s -> Arrays.stream (s.split ("[\\p{P}\\p{Z}\\p{Digit}]")).
                map (String::toLowerCase)).
                filter (word -> word.length () >= 4).
                collect (Collectors.toList ());
    }
}