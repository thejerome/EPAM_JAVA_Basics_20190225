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

    private int bottle = 99;
    private int bottleTakenAtOnce;
    private String bottleTaken;
    public BottleSong(int bottleTakenAtOnce) {
        //TODO
        if ( bottleTakenAtOnce <= 0 || bottleTakenAtOnce > 99) {
            throw new IllegalArgumentException();
        }
        this.bottleTakenAtOnce = bottleTakenAtOnce;
        bottleTaken = numToString(bottleTakenAtOnce);
    }

    public String getBottleSongLyrics(){
        StringBuilder sb = new StringBuilder();
        while (bottle >= bottleTakenAtOnce) {
            sb.append(bottle + " bottles of beer on the wall, " + bottle + " bottles of beer.\n");
            sb.append("Take " + bottleTaken + " down and pass around, ");
            bottle -= bottleTakenAtOnce;
            if (bottle == 1) {
                sb.append(bottle + " bottle of beer on the wall.\n");
                sb.append(bottle + " bottle of beer on the wall, " + bottle + " bottle of beer.\n");
                sb.append("Take " + numToString(bottle) + " down and pass around, no more bottles of beer on the wall.\n");
                bottle = 0;
            } else if(bottle == 0) {
                sb.append("no more bottles of beer on the wall.\n");
            } else {
                sb.append(bottle + " bottles of beer on the wall.\n");
            }
        }
        if (bottle < bottleTakenAtOnce && bottle > 0) {
            sb.append(bottle + " bottles of beer on the wall, " + bottle + " bottles of beer.\n");
            sb.append("Take " + numToString(bottle) + " down and pass around, no more bottles of beer on the wall.\n");
            bottle = 0;
        }
        sb.append("No more bottles of beer on the wall, no more bottles of beer.\n");
        sb.append("Go to the store and buy some more, 99 bottles of beer on the wall.\n");
        return sb.toString();
    }
    private String numToString(int num) {
        String[] array = new String[100];
        array[0] = "";
        array[1] = "one";
        array[2] = "two";
        array[3] = "three";
        array[4] = "four";
        array[5] = "five";
        array[6] = "six";
        array[7] = "seven";
        array[8] = "eight";
        array[9] = "nine";
        array[10] = "ten";
        array[11] = "eleven";
        array[12] = "twelve";
        array[13] = "thirteen";
        array[14] = "fourteen";
        array[15] = "fifteen";
        array[16] = "sixteen";
        array[17] = "seventeen";
        array[18] = "eighteen";
        array[19] = "nineteen";
        array[20] = "twenty";
        array[30] = "thirty";
        array[40] = "forty";
        array[50] = "fifty";
        array[60] = "sixty";
        array[70] = "seventy";
        array[80] = "eighty";
        array[90] = "ninety";
        StringBuilder sb = new StringBuilder();
        if (num / 10 > 1 && num % 10 != 0) {
            sb.append(array[(num / 10) * 10]).append(" ").append(array[num % 10]);
        }
        if (num / 10 > 1 && num % 10 == 0) {
            sb.append(array[(num / 10) * 10]);
        }
        if (num / 10 == 1) {
            sb.append(array[num]);
        }
        if (num / 10 == 0) {
            sb.append(array[num]);
        }
        return sb.toString();
    }
}