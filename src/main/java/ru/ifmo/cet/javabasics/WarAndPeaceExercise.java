package ru.ifmo.cet.javabasics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //private List<String> words = new ArrayList;

        Map<String,Integer> words = new HashMap();
        readVolume(tome12Path, words);
        readVolume(tome34Path, words);
        String wordsCounted = toString(words);
        System.out.println("проверка кириллицы");
        Locale rusLoc = new Locale("ru","RU");
        System.out.printf(rusLoc,wordsCounted);
        return wordsCounted;
        //throw new UnsupportedOperationException();
    }
    private static void readVolume(Path path, Map<String, Integer> words){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path.toString()), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                //String[] lines = line.toLowerCase().split("\\W");
                String[] lines = line.toLowerCase().split("\\s");
                for(String str:lines){
                    if(words.containsKey(str)){
                        words.put(str, words.get(str) + 1);
                    }else{
                        words.put(str,1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String toString(Map<String, Integer> words){
        StringBuffer sb = new StringBuffer("");

        Set<Map.Entry<String, Integer>> entries = words.entrySet();
        for(Map.Entry<String, Integer> entry : entries){
            sb.append( entry.getKey() + " - " + entry.getValue() + "\n");
        }
        return sb.toString();
    }
}