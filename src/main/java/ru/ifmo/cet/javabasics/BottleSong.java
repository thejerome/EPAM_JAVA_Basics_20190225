package ru.ifmo.cet.javabasics;

import java.util.Formatter;

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
    private final String song;
    private final String[] numbersByWord = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };
    private final String[] tensByWord = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99) {
            throw new IllegalArgumentException();
        }
        song = generateSong(bottleTakenAtOnce);
    }

    private String generateSong(int bottleTakenAtOnce) {
        int currentBottles;
        Formatter formatter;
        String takeAtOnce;
        String bottle;

        formatter = new Formatter();
        takeAtOnce = generateNumbersByWord(bottleTakenAtOnce);
        currentBottles = 99;
        bottle = "bottles";

        while (currentBottles > bottleTakenAtOnce) {
            formatter.format("%1$d %2$s of beer on the wall, %1$d %2$s of beer.\n", currentBottles, bottle);
            currentBottles -= bottleTakenAtOnce;
            if (currentBottles == 1) bottle = "bottle";
            formatter.format("Take%1$s down and pass around, %2$d %3$s of beer on the wall.\n", takeAtOnce, currentBottles, bottle);
        }
        if (currentBottles - bottleTakenAtOnce <= 0) {
            formatter.format("%1$d %2$s of beer on the wall, %1$d %2$s of beer.%n", currentBottles, bottle)
                    .format("Take%1$s down and pass around, no more bottles of beer on the wall.\n", generateNumbersByWord(currentBottles))
                    .format("No more bottles of beer on the wall, no more bottles of beer.\n")
                    .format("Go to the store and buy some more, 99 bottles of beer on the wall.\n");
        }
        return formatter.toString();
    }

    private String generateNumbersByWord(int numberToGenerate) {
        if (numberToGenerate > 0 && numberToGenerate < 20)
            return numbersByWord[numberToGenerate];
        else if (numberToGenerate <= 99)
            return tensByWord[numberToGenerate / 10] + numbersByWord[numberToGenerate % 10];

        return String.valueOf(numberToGenerate);
    }

    public String getBottleSongLyrics() {
        return song;
    }
}
