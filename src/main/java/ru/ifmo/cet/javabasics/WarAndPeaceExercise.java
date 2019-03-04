package ru.ifmo.cet.javabasics;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {
   private static String result;
   private static WarAndPeaceExercise warAndPeaceExercise = new WarAndPeaceExercise();
   private static TreeMap<String, Integer> wordMap = new TreeMap<>();

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        warAndPeaceExercise.readAndParseWAP(tome12Path.toFile());
        warAndPeaceExercise.readAndParseWAP(tome34Path.toFile());
        warAndPeaceExercise.makingResult(entriesSortedByValues(wordMap));

        return result;
    }

    public void readAndParseWAP(File tome) {
        try (BufferedReader tomeReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(tome), "CP1251"))) {

            while (tomeReader.ready()) {
                String[] words = tomeReader.readLine().toLowerCase().split("[^A-Za-zА-Яа-я]");
                for (String word: words) {
                    if (wordMap.containsKey(word)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        if (word.length() >= 4) wordMap.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makingResult(SortedSet<Map.Entry<String,Integer>> wordSet) {
        Formatter formatter = new Formatter();
        for (Map.Entry<String, Integer> entry: wordSet){
            String word = entry.getKey();
            int amount = entry.getValue();

            if(amount >= 10) {
                formatter.format("%1$s - %2$d\n", word, amount);
            }
        }
        result = formatter.toString().trim();
    }

    private static SortedSet<Map.Entry<String,Integer>> entriesSortedByValues(Map<String,Integer> map) {
        SortedSet<Map.Entry<String,Integer>> sortedEntries = new TreeSet<>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        int res = -e1.getValue().compareTo(e2.getValue());
                        if (res == 0) res = e1.getKey().compareTo(e2.getKey());
                        return res;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}