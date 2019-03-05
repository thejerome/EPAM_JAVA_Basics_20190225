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

        List <String> book = new ArrayList<>();

        try{
            book.addAll(Files.readAllLines(tome12Path, Charset.forName("Windows-1251")));
            book.addAll(Files.readAllLines(tome34Path, Charset.forName("Windows-1251")));
        } catch (IOException ex) {
            throw new UnsupportedOperationException("Exception!");
        }
        Map<String, Integer> dictionary = new HashMap<>();
        Integer count;
        String wordLC;

        //filling in the dictionary with words
        for (String s : book){
            for (String word:s.split("[^a-zA-Zа-яА-Я]")){
                if (word.length()<4){
                    continue;
                }
                wordLC = word.toLowerCase();
                count = dictionary.get(wordLC);
                if (count == null){
                    dictionary.put(wordLC,1);
                } else {
                    dictionary.put(wordLC,count+1);
                }
            }
        }

        //move the dictionary to list and sort
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(dictionary.entrySet());
        Comparator <Map.Entry<String,Integer>> entryComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue()<o2.getValue()){
                    return 1;
                } else if (o1.getValue()>o2.getValue()) {
                    return -1;
                }
                return o1.getKey().compareTo(o2.getKey());
            }
        };
        entries.sort(entryComparator);


        //adding compatible words to builder
        StringBuilder builder = new StringBuilder();
        for ( Map.Entry<String, Integer> entry: entries) {
            if (entry.getValue()<10){
                builder.deleteCharAt(0);
                break;
            }
            builder.append(String.format("\n%s - %d", entry.getKey(), entry.getValue()));
        }
        return builder.toString();
    }


}