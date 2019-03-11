package ru.ifmo.cet.javabasics;
public class BottleSong {
    private int bottleTakeAtOnce = 0;
    public BottleSong(int bottleTakenAtOnce) {
        this.bottleTakeAtOnce = bottleTakenAtOnce;
    }
    public String getBottleSongLyrics() {
        String number[] = new String[100];
        number[1] = "one";
        number[2] = "two";
        number[3] = "three";
        number[4] = "four";
        number[5] = "five";
        number[6] = "six";
        number[7] = "seven";
        number[8] = "eight";
        number[9] = "nine";
        number[10] = "ten";
        number[11] = "eleven";
        number[12] = "twelve";
        number[13] = "thirteen";
        number[14] = "fourteen";
        number[15] = "fifteen";
        number[16] = "sixteen";
        number[17] = "seventeen";
        number[18] = "eighteen";
        number[19] = "nineteen";
        number[20] = "twenty";
        number[30] = "thirty";
        number[40] = "forty";
        number[50] = "fifty";
        number[60]  = "sixty";
        number[70] = "seventy";
        number[80] = "eighty";
        number[90] = "ninety";
        String song = "";
        String left = "";
        String wordNumber = "";
        String lastWord = "";
        if (bottleTakeAtOnce > 0 && bottleTakeAtOnce < 100) {
            if (bottleTakeAtOnce < 20) {
                wordNumber = number[bottleTakeAtOnce];
            } else {
                int tens = bottleTakeAtOnce / 10;
                int unit = bottleTakeAtOnce % 10;
                wordNumber = number[tens * 10];
                if (unit != 0) {
                    wordNumber = wordNumber + " " + number[unit];
                }
            }
            if (99 % bottleTakeAtOnce < 20) {
                lastWord = number[99 % bottleTakeAtOnce];
            } else {
                int tens = 99 % bottleTakeAtOnce / 10;
                int unit = 99 % bottleTakeAtOnce % 10;
                lastWord = number[tens * 10];
                if (unit != 0) {
                    lastWord = lastWord + " " + number[unit];
                }
            }
            for (int i = 99; i > 0; i = i - bottleTakeAtOnce) {
                left = Integer.toString(i);
                if (i == 1) {
                    song = song + "1 bottle of beer on the wall, 1 bottle of beer.\n" +
                            "Take one down and pass around, no more bottles of beer on the wall.\n";
                } else if (i < bottleTakeAtOnce) {
                    song = song + left + " bottles of beer on the wall, " + left + " bottles of beer.\n" +
                            "Take " + lastWord + " down and pass around, no more bottles of beer on the wall.\n";
                } else if (i == bottleTakeAtOnce) {
                    song = song + left + " bottles of beer on the wall, " + left + " bottles of beer.\n" +
                            "Take " + wordNumber + " down and pass around, no more bottles of beer on the wall.\n";
                } else {
                    song = song + left + " bottles of beer on the wall, " + left + " bottles of beer.\n";
                    if (i - bottleTakeAtOnce == 1) {
                        song = song + "Take " + wordNumber + " down and pass around, " + Integer.toString(i - bottleTakeAtOnce) + " bottle of beer on the wall.\n";
                    } else {
                        song = song + "Take " + wordNumber + " down and pass around, " + Integer.toString(i - bottleTakeAtOnce) + " bottles of beer on the wall.\n";
                    }
                }
                song = song;
            }
        } else {
            throw new java.lang.IllegalArgumentException();
        }
        return song + "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
    }
}
