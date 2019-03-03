package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


class WarAndPeaceExercise {
    private Map<String, Integer> result = new HashMap<>();

    static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        WarAndPeaceExercise exercise = new WarAndPeaceExercise();
        exercise.readTheFile(tome12Path);
        exercise.readTheFile(tome34Path);
        return exercise.getResult();
    }

    private String getResult() {
        StringBuilder result = new StringBuilder();
        this.sortByValue(this.result);
        for (Map.Entry<String, Integer> entry : this.result.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value < 10) continue;
            result.append(key)
                    .append(" - ")
                    .append(value)
                    .append("\n");
        }
        result.deleteCharAt(result.lastIndexOf("\n"));
        return result.toString();
    }

    private void sortByValue(Map<String, Integer> mapToSort) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(mapToSort.entrySet());

        // Sort the list
        list.sort((o1, o2) -> {
            if (o1.getValue().compareTo(o2.getValue()) == 0)
                return o1.getKey().compareTo(o2.getKey());
            return -(o1.getValue()).compareTo(o2.getValue());
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        this.result = temp;
    }

    private void readTheFile(Path fileToRead) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(
                        new File(String.valueOf(fileToRead))), Charset.forName("windows-1251"))
        )) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                for (String word :
                        temp.split("[^a-zA-Zа-яА-Я]")) {
                    if (word != null && word.length() >= 4) {
                        word = word.toLowerCase();
                        int n = this.result.get(word) == null ? 0 : this.result.get(word);
                        if (n >= 0) {
                            this.result.put(word, ++n);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}