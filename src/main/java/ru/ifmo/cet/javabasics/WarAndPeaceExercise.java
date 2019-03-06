package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WarAndPeaceExercise {

    private static String result;
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");



        return result;
    }


    private BufferedReader concatinatedTomes(Path tome12Path, Path tome34Path) throws IOException {
        InputStream inputStream1 = new FileInputStream(tome12Path.toFile());
        InputStream inputStream2 = new FileInputStream(tome12Path.toFile());
        InputStream inputStream = new SequenceInputStream(inputStream1, inputStream2);
        return new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));
    }

    private void countTheWordsToMap(BufferedReader br) throws IOException {

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
    }



}