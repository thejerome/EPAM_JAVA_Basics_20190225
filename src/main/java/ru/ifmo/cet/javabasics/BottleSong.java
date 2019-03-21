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

    private static int bottels = 99;
    private String[] oneZeroArray = {"zero","one","two","three", "four", "five","six","seven","eight","nine"};
    private String[] tensArray = {"Zero","Ten","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    private String[] teensArray = {"Zero","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};


    public BottleSong(int bottleTakenAtOnce) {
        //TODO
        if(bottleTakenAtOnce < 0 || bottleTakenAtOnce > 99)
        {
            System.out.println("No no no");
        }
        else if (bottleTakenAtOnce >= bottels) {
            getBottleSongLyrics(bottels);
            getBottleSongLyrics(0);
            bottels = 99;
        } else {
            getBottleSongLyrics(bottleTakenAtOnce);
            bottels -= bottleTakenAtOnce;
        }
    }

    public void getBottleSongLyrics(int bottelshere)
    {
        //TODO
        if(bottelshere == 0)
        {
            System.out.println("No more bottles of beer on the wall, no more bottles of beer");
            System.out.println("Go to the store and buy some more");
            System.out.println("99 bottles of beer on the wall, 99 bottles of beer");
        }
        else {
            if (bottelshere < 10) {
                System.out.println(bottels + " bottles of beer on the wall, " + bottels + "  bottles of beer");
                System.out.println("Take " + oneZeroArray[bottelshere] + " down, pass it around, " + (bottels - bottelshere) + " bottles of beer");
            } else if (bottelshere % 10 == 0) {
                System.out.println(bottels + " bottles of beer on the wall, " + bottels + "  bottles of beer");
                System.out.println("Take " + tensArray[bottelshere / 10] + " down, pass it around, " + (bottels - bottelshere) + " bottles of beer");
            } else if (bottelshere > 10 && bottelshere < 20) {
                System.out.println(bottels + " bottles of beer on the wall, " + bottels + "  bottles of beer");
                System.out.println("Take " + teensArray[bottelshere % 10] + " down, pass it around, " + (bottels - bottelshere) + " bottles of beer");
            } else {
                System.out.println(bottels + " bottles of beer on the wall, " + bottels + "  bottles of beer");
                System.out.println("Take " + tensArray[bottelshere / 10] + " " + oneZeroArray[bottelshere % 10] + " down, pass it around, " + (bottels - bottelshere) + " bottles of beer");
            }
        }
    }
}
