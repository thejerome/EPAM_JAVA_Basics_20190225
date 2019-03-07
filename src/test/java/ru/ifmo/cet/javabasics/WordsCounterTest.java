package ru.ifmo.cet.javabasics;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordsCounterTest {

    @Test
    public void countWords() {
        List<String> list = new LinkedList<> ();
        list.add ("пряник");
        list.add ("кошка");
        list.add ("кружка");
        list.add ("портрет");
        list.add ("кошка");
        list.add ("кружка");
        list.add ("чего");
        list.add ("хотите");
        list.add ("кружка");
        list.add ("пряник");
        list.add ("меня");
        list.add ("пряник");
        list.add ("кружка");
        list.add ("князь");

        WordsCounter wordsCounter = new WordsCounter ();

        HashMap<String, Integer> actual = wordsCounter.countWords (list);
        HashMap<String, Integer> expected = new HashMap<> ();
        expected.put ("князь", 1);
        expected.put ("пряник", 3);
        expected.put ("чего", 1);
        expected.put ("хотите", 1);
        expected.put ("кошка", 2);
        expected.put ("меня", 1);
        expected.put ("портрет", 1);
        expected.put ("кружка", 4);

        assertEquals (expected, actual);
    }
}