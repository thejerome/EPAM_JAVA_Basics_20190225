package ru.ifmo.cet.javabasics;

import java.util.Locale;
import java.util.ResourceBundle;

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

    public BottleSong(int bottleTakenAtOnce) {
        this.bottleTakenAtOnce = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {

        if (bottleTakenAtOnce <= 0 || bottleTakenAtOnce > 99){
            throw new IllegalArgumentException();
        }

        int bottlesLeft = 99;
        String bottleWord;
        String bottlesLeftWord;
        StringBuilder song = new StringBuilder();
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("ru.ifmo.cet.javabasics.Numbers", locale);

        while (bottlesLeft >= bottleTakenAtOnce){

            if (bottlesLeft == 1){
                bottleWord = "bottle";
            } else {
                bottleWord = "bottles";
            }

            String firstSentence = bottlesLeft + " " + bottleWord + " of beer on the wall, "
                    + bottlesLeft + " " + bottleWord + " of beer.\n";

            bottlesLeft = bottlesLeft - bottleTakenAtOnce;

            if (bottlesLeft == 1){
                bottleWord = "bottle";
            } else {
                bottleWord = "bottles";
            }

            if (bottlesLeft == 0){
                bottlesLeftWord = "no more";
            } else {
                bottlesLeftWord = String.valueOf(bottlesLeft);
            }

            String secondSentence = "Take " + bundle.getString(String.valueOf(bottleTakenAtOnce)) +
                    " down and pass around, " + bottlesLeftWord + " " + bottleWord + " of beer on the wall.\n";

            song.append(firstSentence);
            song.append(secondSentence);
        }

        if (bottlesLeft != 0){

            if (bottlesLeft == 1){
                bottleWord = "bottle";
            } else {
                bottleWord = "bottles";
            }

            String firstSentence = bottlesLeft + " " + bottleWord + " of beer on the wall, "
                    + bottlesLeft + " " + bottleWord + " of beer.\n";

            bottleWord = "bottles";
            bottlesLeftWord = "no more";

            String secondSentence = "Take " + bundle.getString(String.valueOf(bottlesLeft)) +
                    " down and pass around, " + bottlesLeftWord + " " + bottleWord + " of beer on the wall.\n";

            song.append(firstSentence);
            song.append(secondSentence);
        }

        song.append("No more bottles of beer on the wall, no more bottles of beer.\n");
        song.append("Go to the store and buy some more, 99 bottles of beer on the wall.\n");

        return song.toString();
    }
}
