package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class WarAndPeaceExercise {

    public static String warAndPeace()
    {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10


        Map<String, Integer> wordMap = new HashMap<>();

        final String CHARSET = "windows-1251";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new SequenceInputStream
                (new FileInputStream(tome12Path.toFile()), new FileInputStream(tome34Path.toFile())), Charset.forName(CHARSET))))
        {
            String line = null;

            while ((line = br.readLine()) != null)
            {
                String[] item = line.split("[^a-zA-Zа-яА-ЯёЁ]");
                for (String it : item)
                {
                    if (it.length() >= 4)
                        if (wordMap.get(it.toLowerCase()) == null)
                        {
                            wordMap.put(it.toLowerCase(), 1);
                        }
                        else
                        {
                            wordMap.put(it.toLowerCase(), wordMap.get(it.toLowerCase()) + 1);
                        }
                }
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }



        Map<String, Integer> sortedWordMap = sortByValue(wordMap);

        StringBuilder strb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : sortedWordMap.entrySet())
        {
            if (entry.getValue() >= 10)
            {
                strb.append(String.format("%s - %d\n", entry.getKey(), entry.getValue()));
            }
        }

        return strb.toString().trim();
    }


    public static Map<String, Integer> sortByValue(Map<String, Integer> map)
    {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort( new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                int result = -entry1.getValue().compareTo(entry2.getValue());
                if (result == 0)
                    result = entry1.getKey().compareTo(entry2.getKey());
                return result;
            }
        });

        Map<String, Integer> result = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}