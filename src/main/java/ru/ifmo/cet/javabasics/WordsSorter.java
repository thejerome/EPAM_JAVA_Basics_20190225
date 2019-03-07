package ru.ifmo.cet.javabasics;

import java.util.*;

public class WordsSorter {
    public HashMap<String, Integer> sortWords(HashMap<String, Integer> map) {
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<> ();

        List<Map.Entry<String, Integer>> list = new ArrayList<> (map.entrySet ());

        Collections.sort (list, new Comparator<Map.Entry<String, Integer>> () {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int compareTo = o2.getValue ().compareTo (o1.getValue ());
                if (compareTo == 0) {
                    compareTo = o1.getKey ().compareTo (o2.getKey ());
                }
                return compareTo;
            }
        });

        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getValue () >= 10)
                sorted.put (entry.getKey (), entry.getValue ());
        }

        return sorted;
    }
}