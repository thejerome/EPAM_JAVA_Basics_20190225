package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        List<String> stringsFromFile = new ArrayList<> ();

        stringsFromFile.addAll (readFile (tome12Path));
        stringsFromFile.addAll (readFile (tome34Path));

        LineParser parser = new LineParser ();
        List<String> words = new ArrayList<> ();

        for (String s : stringsFromFile) {
            words.addAll (parser.parseLine (s));
        }

        WordsCounter counter = new WordsCounter ();
        HashMap<String, Integer> wordsMap = counter.countWords (words);

        WordsSorter sorter = new WordsSorter ();
        wordsMap = sorter.sortWords (wordsMap);

        StringBuilder sb = new StringBuilder ();

        for (Map.Entry pair : wordsMap.entrySet ()) {
            sb.append (pair.getKey ()).append (" - ").append (pair.getValue ()).append ("\n");
        }

        return sb.toString ().trim ();
        //throw new UnsupportedOperationException();
    }

    private static List<String> readFile(Path path) {
        List<String> stringsFromFile = new ArrayList<> ();
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream (path.toString ()), "cp1251"));
            while (reader.ready ()) {
                stringsFromFile.add (reader.readLine ());
            }
            reader.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return stringsFromFile;
    }
}