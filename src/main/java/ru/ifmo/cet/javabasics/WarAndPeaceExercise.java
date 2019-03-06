package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class WarAndPeaceExercise {

    private static String result;

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        return result;
    }


    private BufferedReader concatinatedTomes(Path tome12Path, Path tome34Path) throws IOException {
        InputStream inputStream1 = new FileInputStream(tome12Path.toFile());
        InputStream inputStream2 = new FileInputStream(tome12Path.toFile());
        InputStream inputStream = new SequenceInputStream(inputStream1, inputStream2);
        return new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));
    }



}