package ru.ifmo.cet.javabasics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsSorter {
    public HashMap<String, Long> sortWords(HashMap<String, Long> map) {

        return map.entrySet ().stream ().
                filter (count -> count.getValue () >= 10).
                sorted (Map.Entry.<String, Long>comparingByValue ().
                        reversed ().
                        thenComparing (Map.Entry.comparingByKey ())).
                collect (Collectors.toMap (Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
    }
}