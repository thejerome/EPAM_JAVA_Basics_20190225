package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


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

        br.lines()
                .flatMap(s -> Arrays.stream(s.split("[^a-zA-Zа-яА-Я]"))
                        .map(String::toLowerCase)
                        .filter(str -> str.length() >= 4))
                .forEach(string -> map.put(string, map.get(string) == null ? 1 : map.get(string) + 1));
        br.close();
    }

    private static Map<String, Integer> sort(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    int comparing = o2.getValue() - o1.getValue();
                    return comparing == 0 ? o1.getKey().compareTo(o2.getKey()) : comparing;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static String getFrequentWords(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= 10)
                .forEach(e -> sb.append(String.format("%s - %d\n", e.getKey(), e.getValue())));
        return sb.toString().trim();
    }
}
