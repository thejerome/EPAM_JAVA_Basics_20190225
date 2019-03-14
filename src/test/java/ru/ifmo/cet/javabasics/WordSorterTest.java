package ru.ifmo.cet.javabasics;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class WordSorterTest {

    @Test
    public void sortWords() {
        LinkedHashMap<String, Long> expected = new LinkedHashMap<> ();
        expected.put ("портрет", 50L);
        expected.put ("кружка", 40L);
        expected.put ("пряник", 30L);
        expected.put ("testA", 20L);
        expected.put ("testB", 20L);
        expected.put ("кошка", 20L);
        expected.put ("князь", 10L);
        expected.put ("меня", 10L);
        expected.put ("хотите", 10L);
        expected.put ("чего", 10L);

        HashMap<String, Long> source = new HashMap<> ();
        source.put ("testD", 5L);
        source.put ("князь", 10L);
        source.put ("пряник", 30L);
        source.put ("чего", 10L);
        source.put ("testA", 20L);
        source.put ("хотите", 10L);
        source.put ("кошка", 20L);
        source.put ("меня", 10L);
        source.put ("портрет", 50L);
        source.put ("кружка", 40L);
        source.put ("testB", 20L);
        source.put ("testC", 5L);

        WordsSorter sorter = new WordsSorter ();
        HashMap<String, Long> actual = sorter.sortWords (source);

        StringBuilder exp = new StringBuilder ();
        StringBuilder act = new StringBuilder ();

        expected.forEach ((key, value) -> exp.append (key).append (" - ").append (value).append ("\n"));
        actual.forEach ((key, value) -> act.append (key).append (" - ").append (value).append ("\n"));

        assertEquals (expected, actual);
        assertEquals (exp.toString ().trim (), act.toString ().trim ());
    }
}