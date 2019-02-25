package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class WAPResult {
    public final String result;

    public WAPResult() throws IOException {
        result = readAllLines(Paths.get("src", "main","resources", "WAPResult.txt")).stream().collect(Collectors.joining("\n"));
    }
}