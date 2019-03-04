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
    private int bottleTakenAtOnce;

    private static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
            "",        // 0
            "",        // 1
            "twenty",  // 2
            "thirty",  // 3
            "forty",   // 4
            "fifty",   // 5
            "sixty",   // 6
            "seventy", // 7
            "eighty",  // 8
            "ninety"   // 9
    };

    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce <= 0 || bottleTakenAtOnce > 99)
            throw new IllegalArgumentException ();

        this.bottleTakenAtOnce = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        StringBuilder sb = new StringBuilder ();
        int bottlesLeft = 99;

        while (bottlesLeft > 0) {
            sb.append (bottlesLeft).append ((bottlesLeft == 1) ? " bottle" : " bottles").
                    append (" of beer on the wall, ").append (bottlesLeft).
                    append ((bottlesLeft == 1) ? " bottle" : " bottles").append (" of beer.\n");

            if (bottlesLeft <= bottleTakenAtOnce) {
                    sb.append ("Take ").append (number (bottlesLeft)).
                        append (" down and pass around, no more bottles of beer on the wall.\n");

                bottlesLeft = 0;
            } else {
                bottlesLeft -= bottleTakenAtOnce;
                sb.append ("Take ").append (number (bottleTakenAtOnce)).append (" down and pass around, ").
                        append (bottlesLeft).append ((bottlesLeft == 1) ? " bottle" : " bottles").
                        append (" of beer on the wall.\n");
            }
        }

        sb.append("No more bottles of beer on the wall, no more bottles of beer.\n").
           append("Go to the store and buy some more, 99 bottles of beer on the wall.\n");

        return sb.toString ();
//        throw new UnsupportedOperationException();
    }

    private static String number(int i) {
        if (i < 20)
            return units[i];
        else return tens[i / 10] + ((i % 10 != 0) ? " " : "") + units[i % 10];
    }
}