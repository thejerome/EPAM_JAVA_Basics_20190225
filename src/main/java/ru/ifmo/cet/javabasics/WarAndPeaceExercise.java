package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");


        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        HashMap<String, Integer> resultTome1234 = new HashMap<>();

        readFileToHashMap(tome12Path, resultTome1234);
        readFileToHashMap(tome34Path, resultTome1234);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(resultTome1234.entrySet());

        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                if (a.getValue().equals(b.getValue())) {
                    return a.getKey().compareTo(b.getKey());
                } else {
                    return -a.getValue().compareTo(b.getValue());
                }
            }
        };

        list.sort(comparator);

        StringBuilder ResultTome1234 = new StringBuilder();
        for (Map.Entry<String, Integer> entry: list) {
            if (entry.getValue() >= 10) {
                ResultTome1234.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
        }
        ResultTome1234.delete(ResultTome1234.length()-1, ResultTome1234.length());

        return ResultTome1234.toString();
    }

    private static void readFileToHashMap(Path path, HashMap<String, Integer> wordCountMap) throws IOException {
        final String regex = "[\\s\\d+.?!\\-(),;«»…:„'“\\[\\]]";
            for (String lineTome : Files.readAllLines(path, Charset.forName("Cp1251"))) {
                String[] words = lineTome.split(regex);
                for (String word : words) {
                    if (word.length() >= 4) {
                        if (wordCountMap.containsKey(word.toLowerCase())) {
                            wordCountMap.put(word.toLowerCase(), wordCountMap.get(word.toLowerCase()) + 1);
                        } else {
                            wordCountMap.put(word.toLowerCase(), 1);
                        }
                    }
                }
            }
    }

}