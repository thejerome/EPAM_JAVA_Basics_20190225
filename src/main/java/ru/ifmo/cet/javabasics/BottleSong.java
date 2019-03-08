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
    private static final int INITIAL_AMOUNT = 99;
    private static final String[] firstTwentyNumbersNames = {
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
    private static final String[] tensNames = {
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

    private final int step;
    private final String stepName;
    private String bottleSongLyrics;


    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > INITIAL_AMOUNT) {
            throw new IllegalArgumentException("Number of bottles must be in range [1, 99].");
        }

        step = bottleTakenAtOnce;
        stepName = intToWord(step);

        createLyrics();
    }

    public String getBottleSongLyrics() {
        return bottleSongLyrics;
    }

    private void createLyrics() {
        int currentAmount = INITIAL_AMOUNT;
        String plural = "s";
        Formatter formatter = new Formatter();

        while (currentAmount > step) {
            formatter.format("%1$d bottles of beer on the wall, %d bottles of beer.\n", currentAmount);
            currentAmount -= step;

            if (currentAmount < 2) {
                plural = "";
            }
            formatter.format("Take%s down and pass around, %d bottle%s of beer on the wall.\n", stepName, currentAmount, plural);
        }

        formatter.format("%1$d bottle%2$s of beer on the wall, %d bottle%s of beer.\n", currentAmount, plural)
                 .format("Take%s down and pass around, no more bottles of beer on the wall.\n", intToWord(currentAmount))
                 .format("No more bottles of beer on the wall, no more bottles of beer.\n")
                 .format("Go to the store and buy some more, %d bottles of beer on the wall.\n", INITIAL_AMOUNT);

        bottleSongLyrics = formatter.toString();
    }

    private String intToWord(int n) {
        if (n < 20) {
            return firstTwentyNumbersNames[n];
        }
        else {
            return tensNames[n / 10] + firstTwentyNumbersNames[n % 10];
        }
    }
}