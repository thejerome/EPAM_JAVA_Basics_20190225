package ru.ifmo.cet.javabasics;

import com.google.common.annotations.VisibleForTesting;

import java.util.LinkedList;
import java.util.List;

public class LineParser {
    @VisibleForTesting
    public List<String> parseLine(String line) {
        List<String> list = new LinkedList<> ();

        String[] tmp = line.split ("[\\p{P}|\\p{Z}]");

        for (String word : tmp) {
            if (word.length () >= 4 && !word.matches ("-?\\d+"))
                list.add (word.toLowerCase ());
        }

        return list;
    }
}