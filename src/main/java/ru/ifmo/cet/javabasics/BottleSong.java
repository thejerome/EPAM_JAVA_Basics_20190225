package ru.ifmo.cet.javabasics;

import java.util.HashMap;
import java.util.Map;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {
    private int bottleTakenAtOnce;
    private Map<Integer, String> numberToStringMapping = new HashMap<>();


    BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99) {
            throw new IllegalArgumentException();
        }
        this.bottleTakenAtOnce = bottleTakenAtOnce;
        initializationOfNumberToStringMapping();
    }

    String getBottleSongLyrics() {
        int remainingBottles = 99;
        StringBuilder resultSongStringBuilder = new StringBuilder().append("99 bottles of beer on the wall, 99 bottles of beer.\nTake ");
        String bottle = " bottles ";
        String bottleTakenAtOnceAsString = convertToString(bottleTakenAtOnce);

        while (remainingBottles > bottleTakenAtOnce) {
            remainingBottles -= bottleTakenAtOnce;
            if (remainingBottles == 1) bottle = " bottle ";
            resultSongStringBuilder
                    .append(bottleTakenAtOnceAsString)
                    .append(" down and pass around, ")
                    .append(remainingBottles)
                    .append(bottle)
                    .append("of beer on the wall.\n")
                    .append(remainingBottles)
                    .append(bottle)
                    .append("of beer on the wall, ")
                    .append(remainingBottles)
                    .append(bottle)
                    .append("of beer.\nTake ");
        }
        return resultSongStringBuilder
                .append(convertToString(Math.abs(remainingBottles)))
                .append(" down and pass around, no more bottles of beer on the wall.\n")
                .append("No more bottles of beer on the wall, no more bottles of beer.\n")
                .append("Go to the store and buy some more, 99 bottles of beer on the wall.\n")
                .toString();
    }

    private String convertToString(int bottleTakenAtOnce) {
        return numberToStringMapping.containsKey(bottleTakenAtOnce) ?
               numberToStringMapping.get(bottleTakenAtOnce) :
               numberToStringMapping.get(bottleTakenAtOnce / 10 * 10) + " " + numberToStringMapping.get(bottleTakenAtOnce % 10);
    }

    private void initializationOfNumberToStringMapping() {
        numberToStringMapping.put(1, "one");
        numberToStringMapping.put(2, "two");              numberToStringMapping.put(3, "three");
        numberToStringMapping.put(4, "four");             numberToStringMapping.put(5, "five");
        numberToStringMapping.put(6, "six");              numberToStringMapping.put(7, "seven");
        numberToStringMapping.put(8, "eight");            numberToStringMapping.put(9, "nine");
        numberToStringMapping.put(10, "ten");             numberToStringMapping.put(11, "eleven");
        numberToStringMapping.put(12, "twelve");          numberToStringMapping.put(13, "thirteen");
        numberToStringMapping.put(14, "fourteen");        numberToStringMapping.put(15, "fifteen");
        numberToStringMapping.put(16, "sixteen");         numberToStringMapping.put(17, "seventeen");
        numberToStringMapping.put(18, "eighteen");        numberToStringMapping.put(19, "nineteen");
        numberToStringMapping.put(20, "twenty");          numberToStringMapping.put(30, "thirty");
        numberToStringMapping.put(40, "forty");           numberToStringMapping.put(50, "fifty");
        numberToStringMapping.put(60, "sixty");           numberToStringMapping.put(70, "seventy");
        numberToStringMapping.put(80, "eighty");          numberToStringMapping.put(90, "ninety");
    }
}