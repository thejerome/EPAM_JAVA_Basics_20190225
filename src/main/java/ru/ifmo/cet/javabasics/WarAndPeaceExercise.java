package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO Iff word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order iff needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        final List<String> linesFromFile = new ArrayList<> ();
        linesFromFile.addAll (readFile (tome12Path));
        linesFromFile.addAll (readFile (tome34Path));

        LineParser parser = new LineParser ();
        final List<String> words = new ArrayList<> ();
        linesFromFile.stream ().map (parser::parseLine).flatMap (Collection::stream).forEach (words::add);

        WordsCounter counter = new WordsCounter ();
        final HashMap<String, Long> countedWords = counter.countWords (words);

        WordsSorter sorter = new WordsSorter ();
        final HashMap<String, Long> sortedWords = sorter.sortWords (countedWords);

        StringBuilder sb = new StringBuilder ();
        sortedWords.forEach ((key, value) -> sb.append (key).append (" - ").append (value).append ("\n"));

        return sb.toString ().trim ();
    }

    private static List<String> readFile(Path p) {
        return Stream.of (p.toString ()).
                    map (Paths::get).flatMap (path -> {
                try {
                    return Files.lines (path, Charset.forName ("cp1251"));
                } catch (IOException e) {
                    e.printStackTrace ();
                }
                return null;
            }).map (String::toString).collect (Collectors.toList ());
    }
}