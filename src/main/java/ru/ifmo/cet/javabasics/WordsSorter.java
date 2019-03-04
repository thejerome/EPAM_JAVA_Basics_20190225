package ru.ifmo.cet.javabasics;

import java.util.*;

class WordsSorter {
    HashMap<String, Integer> sortWords(HashMap<String, Integer> map) {
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<> ();

        Set<Map.Entry<String, Integer>> set = map.entrySet ();

        List<Map.Entry<String, Integer>> list = new ArrayList<> (set);

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