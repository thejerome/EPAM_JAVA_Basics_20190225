package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //throw new UnsupportedOperationException();

        String contentWAP12 = "";
        String contentWAP34 = "";
        try {
            contentWAP12 = new String(
                    Files.readAllBytes(tome12Path),
                    "Windows-1251");

            contentWAP34 = new String(
                    Files.readAllBytes(tome34Path),
                    "Windows-1251");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> wordStatistics = countWords(contentWAP12.toLowerCase(),
                contentWAP34.toLowerCase());

        Iterator<Map.Entry<String, Integer>> iterator = wordStatistics.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getKey().length() < 4 || entry.getValue() < 10){
                iterator.remove();
            }
        }

        Map<String, Integer> sortedWordStatistics = sortByValue(wordStatistics);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedWordStatistics.entrySet()){
            String line = entry.getKey() + " - " + entry.getValue() + "\n";
            stringBuilder.append(line);
        }
        stringBuilder.setLength(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static Map<String, Integer> countWords(String firstPart, String secondPart){
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern pattern = Pattern.compile("[а-яa-z]+");
        Matcher matcher = pattern.matcher(firstPart);

        while(matcher.find()){
            String word = matcher.group();
            if(wordCount.containsKey(word)){
                Integer count = wordCount.get(word);
                count++;
                wordCount.put(word, count);
            } else {
                wordCount.put(word.toLowerCase(), 1);
            }
        }

        matcher = pattern.matcher(secondPart);

        while(matcher.find()){
            String word = matcher.group();
            if(wordCount.containsKey(word)){
                Integer count = wordCount.get(word);
                count++;
                wordCount.put(word, count);
            } else {
                wordCount.put(word.toLowerCase(), 1);
            }
        }

        return wordCount;
    }

    public static <String extends Comparable<? super String>,
            Integer extends Comparable<? super Integer>>
    Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()));

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}