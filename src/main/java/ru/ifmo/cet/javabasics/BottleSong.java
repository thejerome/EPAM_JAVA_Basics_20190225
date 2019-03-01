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
public class BottleSong {
    public static final int BOTTLES_LEFT_DEFAULT = 99;
    public static final String[] ONE_TO_NINE =
            {"", "one", "two", "three", "four",
                    "five", "six", "seven", "eight", "nine"};
    public static final String[] TEN_TO_NINETEEN =
            {"ten", "eleven", "twelve", "thirteen", "fourteen",
                    "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static final String[] TENS_TWENTY_TO_NINETY =
            {"", "", "twenty", "thirty", "forty",
                    "fifty", "sixty", "seventy", "eighty", "ninety"};

    private int bottleTakenAtOnce;
    private int bottlesLeft;

    public BottleSong(int bottleTakenAtOnce) {
        this.bottleTakenAtOnce = bottleTakenAtOnce;
        this.bottlesLeft = BOTTLES_LEFT_DEFAULT;
    }

    public String getBottleSongLyrics() {
        if (bottleTakenAtOnce <= 0 || bottleTakenAtOnce >= 100) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder();
        String bottles = "bottles"; //will be set as "bottle" when one be left
        String bottlesLeftAsString; //needs to replace a number to "no more" string at the last iteration
        String bottleTakenAtOnceString; //will be returned by "getNumberAsString(int num)" method (see below)

        while (bottlesLeft > 0) {
            sb.append(String.format("%d %s of beer on the wall, %1$d %2$s of beer.\n",
                    bottlesLeft, bottles));

            if (bottleTakenAtOnce > bottlesLeft) { //if number of bottles is less than we can take
                bottleTakenAtOnceString = getNumberAsString(bottlesLeft); //take everything that left
                bottlesLeft = 0;
            } else {
                bottleTakenAtOnceString = getNumberAsString(bottleTakenAtOnce);
                bottlesLeft = bottlesLeft - bottleTakenAtOnce;
            }

            if (bottlesLeft == 1) {
                bottles = "bottle";
                bottlesLeftAsString = "1";
            } else if (bottlesLeft == 0) {
                bottles = "bottles";
                bottlesLeftAsString = "no more";
            } else {
                bottlesLeftAsString = String.valueOf(bottlesLeft);
            }

            sb.append(String.format("Take %s down and pass around, %s %s of beer on the wall.\n",
                    bottleTakenAtOnceString, bottlesLeftAsString, bottles));
        }

        sb.append("No more bottles of beer on the wall, no more bottles of beer.\n");
        sb.append("Go to the store and buy some more, " + BOTTLES_LEFT_DEFAULT + " bottles of beer on the wall.\n");
        return sb.toString();
    }

    private String getNumberAsString(int num) {
        int ones = num % 10;
        int tens = num / 10;

        if (num >= 10 && num <= 19) {
            return TEN_TO_NINETEEN[ones];
        }

        if (ones == 0) {
            return TENS_TWENTY_TO_NINETY[tens];
        } else if (tens == 0) {
            return ONE_TO_NINE[ones];
        } else {
            return TENS_TWENTY_TO_NINETY[tens] + " " + ONE_TO_NINE[ones];
        }
    }
}
