package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WarAndPeaceExercise {
    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        Map<String, Integer> map = new HashMap<>();

        try {
            readWordsFromFile(map, tome12Path, tome34Path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> sortedMap = sort(map);

        return getFrequentWords(sortedMap);
    }

    private static void readWordsFromFile(Map<String, Integer> map, Path path1, Path path2) throws IOException {
        InputStream isr1 = new FileInputStream(path1.toFile());
        InputStream isr2 = new FileInputStream(path2.toFile());
        BufferedReader br =
                new BufferedReader(new InputStreamReader(new SequenceInputStream(isr1, isr2), "windows-1251"));

        String s;
        while ((s = br.readLine()) != null) {
            String[] words = s.split("[^a-zA-Zа-яА-Я]");
            for (String word : words) {
                String lowercaseWord = word.toLowerCase();
                if (word.length() >= 4) {
                    map.put(lowercaseWord, map.get(lowercaseWord) == null ? 1 : map.get(lowercaseWord) + 1);
                }
            }
        }
        br.close();
    }

    private static Map<String, Integer> sort(Map<String, Integer> map) {
        Map<String, Integer> result = new TreeMap<>(new ValueComparator(map));
        result.putAll(map);
        return result;
    }

    private static class ValueComparator implements Comparator<String> {
        private Map<String, Integer> map;

        public ValueComparator(Map<String, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(String s1, String s2) {
            int comparing = map.get(s2) - map.get(s1);
            if (comparing == 0) {
                return s1.compareTo(s2);
            }
            return comparing;
        }
    }

    private static String getFrequentWords(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 10) {
                sb.append(String.format("%s - %d\n", entry.getKey(), entry.getValue()));
            }
        }
        return sb.toString().trim();
    }
}