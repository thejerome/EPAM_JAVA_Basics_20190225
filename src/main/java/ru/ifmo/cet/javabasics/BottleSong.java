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
    private String[] tensArray = {"Zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private String[] teensArray = {"Zero", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};


    public BottleSong(int bottleTakenAtOnce) {
        //TODO
        if (bottleTakenAtOnce <= 0 || bottleTakenAtOnce >= 100) {
            throw new IllegalArgumentException();
        } else {
            bottelsis -= bottleTakenAtOnce;
        }

    }

    public String getBottleSongLyrics() {
        //TODO
        String result = "";
        boolean flag = true;

        while (flag == true) {

            int k = bottelswas - bottelsis;

            if (k < 10) {

                if(bottelswas == 1)
                {
                    result += "" + bottelswas + " bottle of beer on the wall, " + bottelswas + " bottle of beer.\n" + "Take " + oneZeroArray[k] + " down and pass around, ";
                }
                else {
                    result += "" + bottelswas + " bottles of beer on the wall, " + bottelswas + " bottles of beer.\n" + "Take " + oneZeroArray[k] + " down and pass around, ";
                }
                if (bottelsis == 1) {
                    result += bottelsis + " bottle of beer on the wall.\n";
                } else if (bottelsis == 0) {
                    result += "no more bottles of beer on the wall.\n";
                } else {
                    result += bottelsis + " bottles of beer on the wall.\n";
                }
            } else if (k % 10 == 0) {
                result += "" + bottelswas + " bottles of beer on the wall, " + bottelswas + " bottles of beer.\n" + "Take " + tensArray[k / 10] + " down and pass around, ";
                if(bottelsis == 0)
                {
                    result += "no more bottles of beer on the wall.\n";
                }
                else
                {
                    result += + bottelsis + " bottles of beer on the wall.\n";
                }
            } else if (k > 10 && k < 20) {
                result += "" + bottelswas + " bottles of beer on the wall, " + bottelswas + " bottles of beer.\n" + "Take " + teensArray[k % 10] + " down and pass around, ";
                if(bottelsis == 0)
                {
                    result += "no more bottles of beer on the wall.\n";
                }
                else
                {
                    result += + bottelsis + " bottles of beer on the wall.\n";
                }
            } else {
                result += "" + bottelswas + " bottles of beer on the wall, " + bottelswas + " bottles of beer.\n" + "Take " + tensArray[k / 10] + " " + oneZeroArray[k % 10] + " down and pass around, ";
                if(bottelsis == 0)
                {
                    result += "no more bottles of beer on the wall.\n";
                }
                else
                {
                    result += + bottelsis + " bottles of beer on the wall.\n";
                }
            }

            if (bottelsis == 0) {
                flag = false;
                bottelswas = 0;
                result += "No more bottles of beer on the wall, no more bottles of beer.\n" + "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
            } else if (bottelsis - k < 0) {
                bottelswas = bottelsis;
                bottelsis = 0;
            } else if (bottelsis - k >= 0) {
                bottelswas = bottelsis;
                bottelsis = bottelsis - k;
            }
            //throw new UnsupportedOperationException();
        }
        bottelswas = 99;
        bottelsis = 99;
        return result;
    }
}

