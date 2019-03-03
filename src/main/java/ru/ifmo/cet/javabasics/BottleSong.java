package ru.ifmo.cet.javabasics;

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

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class BottleSong {

    private int bottlesTakenAtOnce;
    private int bottlesOnTheWall = 99;
    private Map<Integer, String> numsMap = new HashMap();
    private Formatter formatter = new Formatter();

    public BottleSong(int bottlesTakenAtOnce) {

        if (bottlesTakenAtOnce < 1 || bottlesTakenAtOnce > 99) {
            throw new IllegalArgumentException();
        }
        this.bottlesTakenAtOnce = bottlesTakenAtOnce;
        fillTheTable();

        while (bottlesOnTheWall > 0) {
            formatter.format("%1$d %2$s of beer on the wall, %1$d %2$s of beer.\n",
                    bottlesOnTheWall, checkBottleOrBottles())
                    .format("Take %1$s down and pass around, %2$s %3$s of beer on the wall.\n",
                            convertNumToString(bottlesTakenAtOnce < bottlesOnTheWall
                                    ? bottlesTakenAtOnce : bottlesOnTheWall),
                            reduceBottlesOnTheWall(), checkBottleOrBottles());
        }
        formatter.format("No more bottles of beer on the wall, no more bottles of beer.\n")
                .format("Go to the store and buy some more, 99 bottles of beer on the wall.\n");
    }

    public String getBottleSongLyrics() {
        return formatter.toString();
    }

    private String reduceBottlesOnTheWall() {
        bottlesOnTheWall -= bottlesTakenAtOnce;
        return bottlesOnTheWall > 0 ? "" + bottlesOnTheWall : "no more";
    }
    private String checkBottleOrBottles() {
        return bottlesOnTheWall == 1 ? "bottle" : "bottles";
    }
    private String convertNumToString(int num) {
        if (numsMap.containsKey(num)) {
            return numsMap.get(num);
        } else {
            return numsMap.get(num / 10 * 10) + " " + numsMap.get(num % 10);
        }
    }
    private void fillTheTable() {
        numsMap.put(1, "one");
        numsMap.put(2, "two");
        numsMap.put(3, "three");
        numsMap.put(4, "four");
        numsMap.put(5, "five");
        numsMap.put(6, "six");
        numsMap.put(7, "seven");
        numsMap.put(8, "eight");
        numsMap.put(9, "nine");
        numsMap.put(10, "ten");
        numsMap.put(11, "eleven");
        numsMap.put(12, "twelve");
        numsMap.put(13, "thirteen");
        numsMap.put(14, "fourteen");
        numsMap.put(15, "fifteen");
        numsMap.put(16, "sixteen");
        numsMap.put(17, "seventeen");
        numsMap.put(18, "eighteen");
        numsMap.put(19, "nineteen");
        numsMap.put(20, "twenty");
        numsMap.put(30, "thirty");
        numsMap.put(40, "forty");
        numsMap.put(50, "fifty");
        numsMap.put(60, "sixty");
        numsMap.put(70, "seventy");
        numsMap.put(80, "eighty");
        numsMap.put(90, "ninety");
    }
}
