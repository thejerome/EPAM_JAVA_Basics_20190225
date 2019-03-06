package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

public class WarAndPeaceExercise {

    private static Map<String, Integer> map = new HashMap<>();

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        try {
            countTheWordsToMap(tome12Path);
            countTheWordsToMap(tome34Path);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return turnMapToString(sortTheMap(map));
    }

    private static void countTheWordsToMap(Path tomePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
                new File(String.valueOf(tomePath))), "windows-1251"));

        while(br.ready()) {
            String[] s = br.readLine().toLowerCase().split("[^\\p{L}]");
            for (String word : s) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
        }
        br.close();
    }

    private static Map<String, Integer> sortTheMap(Map<String, Integer> map) {
        Map<String, Integer> filteredMap = new HashMap<>();
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (m.getValue() >= 10 && m.getKey().length() >= 4) {
                filteredMap.put(m.getKey(), m.getValue());
            }
        }
        Map<String, Integer> sortedAndFilteredMap = new TreeMap<>(new sortedMapComparator(map));
        sortedAndFilteredMap.putAll(filteredMap);
        return sortedAndFilteredMap;
    }

    private static class sortedMapComparator implements Comparator<String> {
        private Map<String, Integer> map;
        private sortedMapComparator(Map<String, Integer> map) {
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

    private static String turnMapToString(Map<String, Integer> map) {
        Formatter formatter = new Formatter();
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            formatter.format("%1$s - %2$d\n", m.getKey(), m.getValue());
        }
        return formatter.toString().trim();
    }
}