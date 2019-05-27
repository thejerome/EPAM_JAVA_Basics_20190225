package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


// TODO map lowercased words to its amount in text and concatenate its entries.
// TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
// TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
// TODO Also omit any word with lengths less than 4 and frequency less than 10

public class WarAndPeaceExercise {

        public static String warAndPeace() throws IOException {
            final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
            final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

            // TODO map lowercased words to its amount in text and concatenate its entries.
            // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
            // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
            // TODO Also omit any word with lengths less than 4 and frequency less than 10

        List<String> lines12 = Files.readAllLines(tome12Path.toAbsolutePath(), Charset.forName("windows-1251"));
        List<String> lines34 = Files.readAllLines(tome34Path.toAbsolutePath(), Charset.forName("windows-1251"));
        List<String> allLines = new ArrayList<>();
        allLines.addAll(lines12);
        allLines.addAll(lines34);

        Map<String, Integer> map = new HashMap<>();

        for (String line : allLines) {
            line = line.toLowerCase().replaceAll("[^а-яa-z]", " ");
            String[] words = line.split("\\s");
            for (String word : words) {
                if (word.length() >= 4) {
                    if(map.containsKey(word)) {
                        int count = map.get(word);
                        count++;
                        map.put(word, count);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        Set<Map.Entry<String, Integer>> es = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = es.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> me = iterator.next();
            if (me.getValue() >= 10) {
                StringBuilder sb = new StringBuilder();
                sb.append(me.getKey()).append(" - ").append(me.getValue());
                list.add(sb.toString());
            }
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i1 = o1.lastIndexOf("-");
                int i2 = o2.lastIndexOf("-");
                Integer temp1 = Integer.parseInt(o1.substring(i1 + 2));
                Integer temp2 = Integer.parseInt(o2.substring(i2 + 2));
                if (temp1.compareTo(temp2) > 0) {
                    return -1;
                } else if (temp1.compareTo(temp2) < 0) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < list.size() - 1; i++) {
            result.append(list.get(i) + "\n");
        }
        result.append(list.get(list.size() - 1));
        return result.toString();
    }
}