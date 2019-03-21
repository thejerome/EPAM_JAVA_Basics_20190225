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

    public BottleSong(int bottleTakenAtOnce) {
        private final int BOTTLES_TAKEN_AT_ONCE;
        private final int BOTTLES_AT_START = 99;

        public BottleSong(int bottleTakenAtOnce) {

        if (bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99)
            throw new IllegalArgumentException("The number of bottles should be between 1 and 99.");

        this.BOTTLES_TAKEN_AT_ONCE = bottleTakenAtOnce;
    }
}

    public String getBottleSongLyrics() {
        int remainingBottlesCount = BOTTLES_AT_START;

        StringBuilder result = new StringBuilder();
        Formatter formatter = new Formatter();

        while (remainingBottlesCount > BOTTLES_TAKEN_AT_ONCE) {
            formatter.format("%1$d bottles of beer on the wall, %1$d bottles of beer.\n"
                    + "Take %2$s down and pass around, %3$d %4$s of beer on the wall.\n",
                    remainingBottlesCount,
                    convertNumberToString(BOTTLES_TAKEN_AT_ONCE),
                    remainingBottlesCount - BOTTLES_TAKEN_AT_ONCE,
                    remainingBottlesCount - BOTTLES_TAKEN_AT_ONCE > 1 ? "bottles" : "bottle");
            remainingBottlesCount -= BOTTLES_TAKEN_AT_ONCE;
        }
        formatter.format("%1$d %3$s of beer on the wall, %1$d %3$s of beer.\n"
                + "Take %2$s down and pass around, no more bottles of beer on the wall.\n",
                remainingBottlesCount,
                convertNumberToString(remainingBottlesCount),
                remainingBottlesCount > 1 ? "bottles" : "bottle");
        result.append(formatter.toString());
        result.append("No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n");
        return result.toString();
    }
}

public String convertNumberToString(int number) {

        final String[] DIGITS = {"zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"};

        final String[] DECIMALS = {"undefined", "ten", "twenty", "thirty", "forty",
                "fifty", "sixty", "seventy", "eighty", "ninety"};

        final String[] SECOND_DECIMAL = {"undefined", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        return number < 10 ? DIGITS[number] :
                number % 10 == 0 ? DECIMALS[number / 10] :
                number < 20 ? SECOND_DECIMAL[number % 10] :
                DECIMALS[number / 10] + " " + DIGITS[number % 10];
}
