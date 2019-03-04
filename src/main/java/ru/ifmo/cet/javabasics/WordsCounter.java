package ru.ifmo.cet.javabasics;

import java.util.HashMap;
import java.util.List;

class WordsCounter {
    private HashMap<String, Integer> words = new HashMap<> ();

    HashMap<String, Integer> countWords (List<String> list) {
        for (String word : list) {
            if (words.containsKey (word)) {
                int count = words.get (word);
                count++;
                words.put (word, count);
            } else words.put (word, 1);
        }

        return words;
    }
}