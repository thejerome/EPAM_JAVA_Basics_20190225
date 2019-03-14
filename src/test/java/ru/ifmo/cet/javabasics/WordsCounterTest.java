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

        HashMap<String, Long> actual = wordsCounter.countWords (list);
        HashMap<String, Long> expected = new HashMap<> ();
        expected.put ("князь", 1L);
        expected.put ("пряник", 3L);
        expected.put ("чего", 1L);
        expected.put ("хотите", 1L);
        expected.put ("кошка", 2L);
        expected.put ("меня", 1L);
        expected.put ("портрет", 1L);
        expected.put ("кружка", 4L);

        assertEquals (expected, actual);
    }
}