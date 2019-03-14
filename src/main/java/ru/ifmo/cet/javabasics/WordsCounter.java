package ru.ifmo.cet.javabasics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsCounter {
    public HashMap<String, Long> countWords(List<String> list) {
                Map<String, Long> res = list.stream ().
                collect (Collectors.groupingBy (s -> s, Collectors.counting ())).
                        entrySet ().stream ().
                        collect (Collectors.toMap (Map.Entry::getKey, Map.Entry::getValue));

        return (HashMap<String, Long>) res;
    }
}