package ru.ifmo.cet.javabasics;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    void testLambdas() throws IOException {
        final String source = readAllLines(Paths.get(
            "src", "main", "java", "ru", "ifmo", "cet", "javabasics", "WarAndPeaceExercise.java"
        )).stream().collect(Collectors.joining("\n"));

        assertFalse(matchesRegex(source, "\\Wif\\W"));
        assertFalse(matchesRegex(source, "\\Wfor\\W"));
        assertFalse(matchesRegex(source, "\\Wwhile\\W"));

    }

    private boolean matchesRegex(String source, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        return (matcher.find());
    }
}