package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //throw new UnsupportedOperationException();

        Map<String, Integer> words = new HashMap();
        readVolume(tome12Path, words);
        readVolume(tome34Path, words);
        omitWords(words);
        return sortAndString(words);
    }

    private static void readVolume(Path path, Map<String, Integer> words) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path.toString()), "cp1251"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.toLowerCase().split("[^a-zA-Z|^а-яА-Я]");
                for (String str : lines) {
                    if (words.containsKey(str)) {
                        words.put(str, words.get(str) + 1);
                    } else {
                        if(str.length() >= 4){
                            words.put(str, 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void omitWords(Map<String, Integer> words) {
        Iterator<Map.Entry<String, Integer>> entryIt = words.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<String, Integer> entry = entryIt.next();
                if (entry.getValue() < 10) {
                    entryIt.remove();
            }
        }
    }
    private static String sortAndString(Map<String, Integer> words){
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(words.entrySet());
        sortedWords.sort(Map.Entry.comparingByKey());
        sortedWords.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        StringBuffer sb = new StringBuffer();
        for ( Map.Entry<String, Integer> entry : sortedWords         ) {
            sb.append(entry.getKey() + " - " + entry.getValue() + "\n");
        }
        return sb.toString().trim();
    }
}