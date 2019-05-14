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

    private StringBuilder song;

    public BottleSong(int bottleTakenAtOnce) {

        if (bottleTakenAtOnce > 0 && bottleTakenAtOnce < 100) {
            song = new StringBuilder();
            int NumbersOfBottle = 99;
            song.append(NumbersOfBottle).append(" bottles of beer on the wall, ").append(NumbersOfBottle).append(" bottles of beer.\n");
            while (NumbersOfBottle > bottleTakenAtOnce) {
                song.append("Take ").append(haveNumber(bottleTakenAtOnce)).append("down and pass around, ");
                NumbersOfBottle = NumbersOfBottle - bottleTakenAtOnce;
                song.append(NumbersOfBottle);
                if (NumbersOfBottle == 1) {
                    song.append(" bottle of beer on the wall.\n");
                    song.append(NumbersOfBottle).append(" bottle of beer on the wall, ").append(NumbersOfBottle).append(" bottle of beer.\n");
                } else {
                    song.append(" bottles of beer on the wall.\n");
                    song.append(NumbersOfBottle).append(" bottles of beer on the wall, ").append(NumbersOfBottle).append(" bottles of beer.\n");
                }
            }
            song.append("Take ").append(haveNumber(NumbersOfBottle)).append("down and pass around, ").append("no more bottles of beer on the wall.\n");
            song.append("No more bottles of beer on the wall, no more bottles of beer.\n" +
                    "Go to the store and buy some more, 99 bottles of beer on the wall.\n");

        } else {
            throw new IllegalArgumentException();
        }
    }
    public String getBottleSongLyrics() {
        throw new UnsupportedOperationException();
        return;
    }
    private String haveNumber(int i) {
        final String[] digits = {"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine "};
        final String[] teens = {"", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ", "eighteen ", "nineteen "};
        final String[] tens = {"", "ten ", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};
        if ((i > 10) && (i < 20)) {
            return teens[i % 10];
        }
        else {
            return tens[i / 10] + digits[i % 10];
        }

    }
}
