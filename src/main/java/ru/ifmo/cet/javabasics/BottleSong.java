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
    final int taken;
    int startAmount;
    String[] takenAmount = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
            "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven",
            "twenty eight", "twenty nine", "thirty", "thirty one", "thirty two", "thirty three", "thirty four",
            "thirty five", "thirty six", "thirty seven", "thirty eight", "thirty nine", "forty", "forty one",
            "forty two", "forty three", "forty four", "forty five", "forty six", "forty seven", "forty eight",
            "forty nine", "fifty", "fifty one", "fifty two", "fifty three", "fifty four", "fifty five", "fifty six",
            "fifty seven", "fifty eight", "fifty nine", "sixty", "sixty one", "sixty two", "sixty three", "sixty four",
            "sixty five", "sixty six", "sixty seven", "sixty eight", "sixty nine", "seventy", "seventy one",
            "seventy two", "seventy three", "seventy four", "seventy five", "seventy six", "seventy seven",
            "seventy eight", "seventy nine", "eighty", "eighty one", "eighty two", "eighty three", "eighty four",
            "eighty five", "eighty six", "eighty seven", "eighty eight", "eighty nine", "ninety", "ninety one",
            "ninety two", "ninety three", "ninety four", "ninety five", "ninety six", "ninety seven", "ninety eight",
            "ninety nine"};

    public BottleSong(int bottleTakenAtOnce) {
        startAmount = 99;
        if (bottleTakenAtOnce <= 0 || bottleTakenAtOnce > startAmount) {
            try {
                throw new ArrayIndexOutOfBoundsException();
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        taken = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        String message = "";
        String str1 = "bottles";
        String str2 = "bottles";
        for (int i = startAmount; i > 0; i -= taken) {
            if (i == 1) {
                str1 = "bottle";
            }
            if (i - taken == 1) {
                str2 = "bottle";
            }
            if (i - taken > 0) {
                message += i + " bottles of beer on the wall, " + i + " " + str1 + " of beer.\n" +
                        "Take " + takenAmount[taken - 1] + " down and pass around, " + (i - taken) + " " + str2 + " of beer on the wall.\n";
            } else {
                message += i + " " + str1 + " of beer on the wall, " + i + " " + str1 + " of beer.\n" +
                        "Take " + takenAmount[i - 1] + " down and pass around, no more bottles of beer on the wall.\n" +
                        "No more bottles of beer on the wall, no more bottles of beer.\n" +
                        "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
            }

        }
        return message;
    }

    public static void main(String[] args) {
        BottleSong bottleSong = new BottleSong(9);
        System.out.println(bottleSong.getBottleSongLyrics());
    }
}
