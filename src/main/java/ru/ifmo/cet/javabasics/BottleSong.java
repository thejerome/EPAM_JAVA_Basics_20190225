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
    int bottleStep;
    public BottleSong(int bottleTakenAtOnce) {
        if (bottleTakenAtOnce<1||bottleTakenAtOnce>99){
            bottleStep=0;
        } else {
            bottleStep=bottleTakenAtOnce;
        }
    }


    static String[] lessThan20 = { "", "one", "two", "three", "four","five",
            "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"};

    static String[] multipleOf10 = {"", "", "twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"};

    /**
     * Convert int in [1;99] range to String
     * @param i - integer to be converted
     * @return String representation of {@param i}
     */
    private static String intToString(int i){
        if (i<20){
            return lessThan20[i];
        } else {
            return multipleOf10[i/10]+(i%10==0 ? "" : " "+lessThan20[i%10]);
        }
    }


    public String getBottleSongLyrics() {
        if (bottleStep == 0){
            throw new IllegalArgumentException();
        }
        StringBuilder builder = new StringBuilder();
        int countOfBottles=99;
        String bottleStepString=intToString(bottleStep);
        String amountOfBottlesString="99 bottles";
        while (countOfBottles>bottleStep){
            builder.append(String.format("%s of beer on the wall, %s of beer.\n",amountOfBottlesString,amountOfBottlesString));
            countOfBottles-=bottleStep;
            amountOfBottlesString=countOfBottles+" bottle"+(countOfBottles<2 ? "" : "s");
            builder.append(String.format("Take %s down and pass around, %s of beer on the wall.\n",bottleStepString,amountOfBottlesString));
        }

        builder.append(String.format(   "%s of beer on the wall, %s of beer.\n" +
                                        "Take %s down and pass around, no more bottles of beer on the wall.\n" +
                                        "No more bottles of beer on the wall, no more bottles of beer.\n" +
                                        "Go to the store and buy some more, 99 bottles of beer on the wall.\n",
                                        amountOfBottlesString,
                                        amountOfBottlesString,
                                        intToString(countOfBottles)));
        return builder.toString();
    }
}
