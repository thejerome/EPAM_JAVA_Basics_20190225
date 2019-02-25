package ru.ifmo.cet.javabasics;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by EE on 2018-09-20.
 */
class WarAndPeaceExerciseTest {

    @Test
    void warAndPeace() throws IOException {
        final String result = WarAndPeaceExercise.warAndPeace();
        assertEquals(new WAPResult().result, result);
    }

    @Test
    void testNoLambdas() throws IOException {
        final String source = readAllLines(Paths.get(
            "src", "main", "java", "ru", "ifmo", "cet", "javabasics", "WarAndPeaceExercise.java"
        )).stream().collect(Collectors.joining("\n"));

        assertFalse(source.contains("->"));
        assertFalse(source.contains("::"));
        assertFalse(source.contains("stream"));

    }
}