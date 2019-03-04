package ru.ifmo.cet.javabasics;
import java.util.Formatter;

public class BottleSong {

    public String song;
    private final String[] UNITS = new String[] {"", "one", "two", "three", "four", "five",
           "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
           "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private final String[] TENS = new String[] {"", "","twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"};

    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99)
            throw new IllegalArgumentException();
        song = makingSong(bottleTakenAtOnce);
    }

    public String makingSong(int bottleTakenAtOnce) {
        Formatter formatter = new Formatter();
        int remainBottles = 99;
        String takenBottle = getWordByNumber(bottleTakenAtOnce);
        String bottle = "bottles";

        while (remainBottles > bottleTakenAtOnce) {
            formatter.format("%1$d bottles of beer on the wall, %1$d bottles of beer.\n", remainBottles);
            remainBottles -= bottleTakenAtOnce;
            if (remainBottles == 1) bottle = "bottle";
            formatter.format("Take %1$s down and pass around, %2$d %3$s of beer on the wall.\n", takenBottle, remainBottles, bottle);
        }

        formatter.format("%1$d %2$s of beer on the wall, %1$d %2$s of beer.\n", remainBottles, bottle)
                 .format("Take %1$s down and pass around, no more bottles of beer on the wall.\n", getWordByNumber(remainBottles))
                 .format("No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n");


        return formatter.toString();
    }

    public String getWordByNumber(int bottles) {
        int tens;
        int units;
        if (bottles > 19) {
            units = bottles % 10;
            tens = (bottles / 10);
            String result = "" + TENS[tens] + " " + UNITS[units];
            return result.trim();
        } else {
            return UNITS[bottles];
        }
    }

    public String getBottleSongLyrics() {
        return song;
    }
}
