package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        Map<String, Integer> dictionary = new HashMap<>();

        fillDictionaryFromFile(dictionary, tome12Path);
        fillDictionaryFromFile(dictionary, tome34Path);

        List<Map.Entry<String,Integer>> sortedEntries = mapToSortedList(dictionary);

        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            if (entry.getValue() < 10) {
                break;
            }
            stringJoiner.add(entry.getKey() + " - " + entry.getValue());
        }

        return stringJoiner.toString();
    }


    private static void fillDictionaryFromFile(Map<String, Integer> dictionary, Path path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path, Charset.forName("windows-1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lines != null) {
            for (String line : lines) {
                line = line.toLowerCase();
                String[] words = line.split("[^\\p{L}]+");

                for (String word : words) {
                    if (word.length() >= 4) {
                        int value = 1;
                        if (dictionary.containsKey(word)) {
                            value += dictionary.get(word);
                        }
                        dictionary.put(word, value);
                    }
                }
            }
        }
    }

    private static List<Map.Entry<String, Integer>> mapToSortedList(Map<String, Integer> dictionary) {
        List<Map.Entry<String,Integer>> entries = new ArrayList<>(dictionary.entrySet());

        Comparator<Map.Entry<String, Integer>> comparatorByValue = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        };

        Comparator<Map.Entry<String, Integer>> comparatorByKey = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        };

        entries.sort(comparatorByValue.thenComparing(comparatorByKey));

        return entries;
    }

}