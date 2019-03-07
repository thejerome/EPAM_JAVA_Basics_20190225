package ru.ifmo.cet.javabasics;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class WordsSorterTest {

    @Test
    public void sortWords() {
        LinkedHashMap<String, Integer> expected = new LinkedHashMap<> ();
        expected.put ("портрет", 50);
        expected.put ("кружка", 40);
        expected.put ("пряник", 30);
        expected.put ("testA", 20);
        expected.put ("testB", 20);
        expected.put ("кошка", 20);
        expected.put ("князь", 10);
        expected.put ("меня", 10);
        expected.put ("хотите", 10);
        expected.put ("чего", 10);


        HashMap<String, Integer> source = new HashMap<> ();
        source.put ("testD", 5);
        source.put ("князь", 10);
        source.put ("пряник", 30);
        source.put ("чего", 10);
        source.put ("testA", 20);
        source.put ("хотите", 10);
        source.put ("кошка", 20);
        source.put ("меня", 10);
        source.put ("портрет", 50);
        source.put ("кружка", 40);
        source.put ("testB", 20);
        source.put ("testC", 5);

        WordsSorter sorter = new WordsSorter ();
        HashMap<String, Integer> actual = sorter.sortWords (source);

        assertEquals (expected, actual);
    }
}