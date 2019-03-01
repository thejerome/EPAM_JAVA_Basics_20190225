package ru.ifmo.cet.javabasics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Properties;


public class BottleSong {

    public String song;
    Properties properties = new Properties();

    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99)
            throw new IllegalArgumentException();

        try (FileInputStream propertyStream =
                     new FileInputStream("src\\main\\resources\\numberByWord.properties")) {
            properties.load(propertyStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (bottles > 19 && bottles % 10 != 0) {
            units = bottles % 10;
            tens = (bottles / 10) * 10;

            return properties.getProperty(String.valueOf(tens)) +
                    " " + properties.getProperty(String.valueOf(units));

        } else {
            return properties.getProperty(String.valueOf(bottles));
        }
    }

    public String getBottleSongLyrics() {
        return song;
    }
}
