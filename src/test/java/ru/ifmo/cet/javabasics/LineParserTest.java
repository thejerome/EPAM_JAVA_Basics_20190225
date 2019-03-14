package ru.ifmo.cet.javabasics;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineParserTest {

    @Test
    public void parseLine() {
        LineParser parser = new LineParser ();

        String test = "– Que voulez vous? Lafater aurait dit que je n'ai pas la bosse de la paterienite, [Чего вы хотите? Лафатер сказал бы, что у меня нет шишки родительской любви,] – сказал князь.";

        List<String> actual = parser.parseLine (test);

        List<String> expected = new LinkedList<> ();
        expected.add ("voulez");
        expected.add ("vous");
        expected.add ("lafater");
        expected.add ("aurait");
        expected.add ("bosse");
        expected.add ("paterienite");
        expected.add ("чего");
        expected.add ("хотите");
        expected.add ("лафатер");
        expected.add ("сказал");
        expected.add ("меня");
        expected.add ("шишки");
        expected.add ("родительской");
        expected.add ("любви");
        expected.add ("сказал");
        expected.add ("князь");

        assertEquals (expected, actual);
    }
}