package ru.ifmo.cet.javabasics;

public class BottleSong {

int bottleTakenAtOnes;

    public BottleSong(int bottleTakenAtOnce) {

        this.bottleTakenAtOnes = bottleTakenAtOnce;

    }

    public String getBottleSongLyrics(){

        if (bottleTakenAtOnes > 99 || bottleTakenAtOnes < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder bottleSongText = new StringBuilder();

        for (int i = 99; i > 0; i--) {

            if ((i - bottleTakenAtOnes) > 0) {
                bottleSongText.append(i + " " + sinOrPlu(i) + " of beer on the wall, " + i + " " + sinOrPlu(i) +
                        " of beer.\n" + "Take " + numberToLetter(bottleTakenAtOnes) + " down and pass around, " +
                        (i - bottleTakenAtOnes) + " " + sinOrPlu(i - bottleTakenAtOnes) + " of beer on the wall.\n");
                i = i + 1 - bottleTakenAtOnes;
            } else if ((i - bottleTakenAtOnes) == 0){
                bottleSongText.append(i + " " + sinOrPlu(i) + " of beer on the wall, " + i + " " + sinOrPlu(i) +
                        " of beer.\n" + "Take " + numberToLetter(bottleTakenAtOnes) +
                        " down and pass around, no more bottles of beer on the wall.\n" +
                        "No more bottles of beer on the wall, no more bottles of beer.\n" +
                        "Go to the store and buy some more, 99 bottles of beer on the wall.\n");
                break;
            } else {
                bottleSongText.append(i + " " + sinOrPlu(i) + " of beer on the wall, " + i + " " + sinOrPlu(i) +
                        " of beer.\n" + "Take " + numberToLetter(i) +
                        " down and pass around, no more bottles of beer on the wall.\n" +
                        "No more bottles of beer on the wall, no more bottles of beer.\n" +
                        "Go to the store and buy some more, 99 bottles of beer on the wall.\n");
                break;
            }
        }
        return bottleSongText.toString();
    }

    public String numberToLetter(int n) {

        String[] lessTwenty = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen"};

        String[] tens = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
                "ninety"};

        if (n < 20) {
            return lessTwenty[n];
        } else if (n%10 == 0) {
            return tens[n/10];
        } else {
            return tens[n/10] + " " + lessTwenty[n%10];
        }
    }

    public String sinOrPlu(int n) {

        if (n == 1) {
            return "bottle";
        } else {
            return "bottles";
        }
    }
}
