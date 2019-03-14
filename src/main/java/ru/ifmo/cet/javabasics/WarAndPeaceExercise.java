package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.ToLongFunction;
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

        final Stream<Map.Entry<String, Long>> words = Stream.of (tome12Path.toString (), tome34Path.toString ()).
                map (Paths::get).flatMap (path -> {
            try {
                return Files.lines (path, Charset.forName ("cp1251"));
            } catch (IOException e) {
                e.printStackTrace ();
            }
            return null;
        }).flatMap (line -> Arrays.stream (line.split ("\\p{P}|\\p{Z}|\\p{Digit}")).
                map (String::toLowerCase)).
                filter (word -> word.length () >= 4).
                collect (Collectors.groupingBy (s -> s, Collectors.counting ())).
                entrySet ().stream ().
                filter (count -> count.getValue () >= 10).
                sorted (Comparator.comparingLong ((ToLongFunction<Map.Entry<String, Long>>) Map.Entry::getValue).
                        reversed ().
                        thenComparing (Map.Entry::getKey));

        StringBuilder sb = new StringBuilder ();
        words.forEach (ele -> sb.append (ele.getKey ()).append (" - ").append (ele.getValue ()).append ("\n"));

        return sb.toString ().trim ();
    }
}