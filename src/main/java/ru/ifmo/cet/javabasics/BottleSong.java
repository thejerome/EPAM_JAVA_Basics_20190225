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

    private static String[] SPECIAL_UNITS =
            {"", "one", "two", "three", "four",
                    "five", "six", "seven", "eight", "nine",
                    "ten", "eleven", "twelve", "thirteen", "fourteen"};
    private static String[] TENS = {"", "", "twen", "thir", "for", "fif", "six", "seven", "eigh", "nine"};

    private int intBottleTakenAtOnce;

    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99) {
            throw new IllegalArgumentException("Amount of bottles should be natural number less than 100");
        }

        intBottleTakenAtOnce = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        Formatter formatter = new Formatter();
        String stringBottleTakenAtOnce = intToString(intBottleTakenAtOnce);
        String bottles = "bottles";

        int bottlesAmount = 99;

        while (bottlesAmount > intBottleTakenAtOnce) {
            formatter.format("%1$d %2$s of beer on the wall, %1$d %2$s of beer.\n", bottlesAmount, bottles)
                    .format("Take %s down and pass around, ", stringBottleTakenAtOnce);

            bottlesAmount -= intBottleTakenAtOnce;
            bottles = bottlesAmount == 1 ? "bottle" : bottles;

            formatter.format("%1$d %2$s of beer on the wall.\n", bottlesAmount, bottles);
        }

        stringBottleTakenAtOnce = intToString(bottlesAmount);

        formatter.format("%1$d %2$s of beer on the wall, %1$d %2$s of beer.\n", bottlesAmount, bottles)
                .format("Take %1$s down and pass around, no more bottles of beer on the wall.\n", stringBottleTakenAtOnce);

        formatter.format("No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n");

        return formatter.toString();
    }

    private String intToString(int bottleTakenAtOnce) {

        if (bottleTakenAtOnce < SPECIAL_UNITS.length) {
            return SPECIAL_UNITS[bottleTakenAtOnce];
        }

        int tens = bottleTakenAtOnce / 10;
        int units = bottleTakenAtOnce % 10;

        if (tens != 1) {
            String separator = units == 0 ? "" : " ";
            return TENS[tens] + "ty" + separator + SPECIAL_UNITS[units];
        } else {
            return TENS[units] + "teen";
        }
    }
}
