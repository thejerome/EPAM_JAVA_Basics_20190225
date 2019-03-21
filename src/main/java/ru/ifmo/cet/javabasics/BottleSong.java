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

    private static int bottelswas = 99;
    private static int bottelsis = 99;
    private String[] oneZeroArray = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private String[] tensArray = {"Zero", "Ten", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] teensArray = {"Zero", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};


    public BottleSong(int bottleTakenAtOnce) {
        //TODO
        if (bottleTakenAtOnce < 0 || bottleTakenAtOnce > 99) {
            throw new IllegalArgumentException();
        } else if (bottleTakenAtOnce >= bottelsis) {

            getBottleSongLyrics();
            bottelsis = 0;
        } else {
            bottelswas = bottelsis;
            bottelsis -= bottleTakenAtOnce;
            getBottleSongLyrics();

        }
    }

    public String getBottleSongLyrics() {
        //TODO
        if (bottelsis == 0) {
            return "No more bottles of beer on the wall, no more bottles of beer\n" + "Go to the store and buy some more, 99 bottles of beer on the wall";
        } else {
            int k = bottelswas - bottelsis;
            if (k < 10) {

                return "" + bottelswas + " bottles of beer on the wall, " + bottelswas + "  bottles of beer\n" + "Take " + oneZeroArray[k] + " down, pass it around, " + (bottelsis) + " bottles of beer";
            } else if (k % 10 == 0) {
                return "" + bottelswas + " bottles of beer on the wall, " + bottelswas + "  bottles of beer\n" + "Take " + tensArray[k / 10] + " down, pass it around, " + bottelsis + " bottles of beer";
            } else if (k > 10 && k < 20) {
                return "" + bottelswas + " bottles of beer on the wall, " + bottelswas + "  bottles of beer\n" + "Take " + teensArray[k % 10] + " down, pass it around, " + bottelsis + " bottles of beer";
            } else {
                return "" + bottelswas + " bottles of beer on the wall, " + bottelswas + "  bottles of beer\n" + "Take " + tensArray[k / 10] + " " + oneZeroArray[k % 10] + " down, pass it around, " + bottelsis + " bottles of beer";
            }
        }
        //throw new UnsupportedOperationException();
    }
}
