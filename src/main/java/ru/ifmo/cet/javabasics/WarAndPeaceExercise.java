package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


class WarAndPeaceExercise {
    private static TreeMap<String, Integer> dictionary = new TreeMap<>();
    private static Set<Words> wordsSet = new TreeSet<>();

    static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        readAndPutInDictionary(tome12Path.toFile());
        readAndPutInDictionary(tome34Path.toFile());
        return writeToFile();
    }

    private static void readAndPutInDictionary(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "windows-1251"))) {
            while (bufferedReader.ready()) {
                String[] strings = bufferedReader.readLine().split("[^а-яА-Яa-zA-Z]");
                for (String word : strings) {
                    String string = word.toLowerCase();
                    if (string.length() >= 4) {
                        if (dictionary.containsKey(string)) {
                            dictionary.put(string, dictionary.get(string) + 1);
                        } else {
                            dictionary.put(string, 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String writeToFile() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> stringIntegerEntry : dictionary.entrySet()) {
            Integer integer = stringIntegerEntry.getValue();
            if (integer > 9) {
                wordsSet.add(new Words(stringIntegerEntry.getKey(), integer));
            }
        }
        for (Words words : wordsSet) {
            stringBuilder.append(words.toString());
        }
        return stringBuilder.toString().trim();
    }

    private static class Words implements Comparable {
        String word;
        Integer amount;

        public Words(String word, Integer amount) {
            this.word = word;
            this.amount = amount;
        }

        @Override
        public int compareTo(Object o) {
            Words words = (Words) o;
            int result = words.amount - this.amount;
            if (result == 0) {
                result = this.word.compareTo(words.word);
            }
            return result;
        }

        @Override
        public String toString() {
            return word + " - " + amount + "\n";
        }
    }
}