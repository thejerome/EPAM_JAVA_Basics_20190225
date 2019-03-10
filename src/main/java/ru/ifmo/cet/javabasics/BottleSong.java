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
    private String BottleSong;
    private int NumberOfBottlesOnTheWall = 99;
    private StringBuilder FirstLine = new StringBuilder(" bottles of beer on the wall,  bottles of beer.");
    private StringBuilder SecondLine = new StringBuilder("Take down and pass around,  bottles of beer on the wall.");
    private StringBuilder BottleSongLyrics = new StringBuilder();
    public BottleSong(int bottleTakenAtOnce) throws IllegalArgumentException {
        if ((bottleTakenAtOnce > 99) || (bottleTakenAtOnce < 1)){
            throw new IllegalArgumentException("Illegal argument");
        }
        String Number = getWrittenNumber(bottleTakenAtOnce);
        while (NumberOfBottlesOnTheWall > bottleTakenAtOnce) {
            BottleSongLyrics.append(getFirstString(NumberOfBottlesOnTheWall));
            BottleSongLyrics.append("\n");
            BottleSongLyrics.append(getSecondString(NumberOfBottlesOnTheWall - bottleTakenAtOnce, Number));
            BottleSongLyrics.append("\n");
            clearBuilder(NumberOfBottlesOnTheWall, Number, bottleTakenAtOnce);
            NumberOfBottlesOnTheWall = NumberOfBottlesOnTheWall - bottleTakenAtOnce;
        }
        ending(NumberOfBottlesOnTheWall);
        BottleSong = BottleSongLyrics.toString();
    }

    private void ending(int Difference){
        BottleSongLyrics.append(getFirstString(NumberOfBottlesOnTheWall)).append("\n");
        String WrittenNumber = getWrittenNumber(Difference);
        BottleSongLyrics.append("Take ").append(WrittenNumber).append("down and pass around, no more bottles of beer on the wall.\nNo more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n");
    }

    public String getBottleSongLyrics() {
        return BottleSong;
    }

    private String getWrittenNumber(int Number){
        String WrittenNumber;
        String Firstpart;
        String Secondpart;
        int Edinitsi = Number % 10;
        int Desyatki = (Number - Edinitsi) / 10;
        if (Desyatki == 1){
            switch (Edinitsi) {
                case 1:
                    WrittenNumber = "eleven ";
                    break;
                case 2:
                    WrittenNumber = "twelve ";
                    break;
                case 3:
                    WrittenNumber = "thirteen ";
                    break;
                case 4:
                    WrittenNumber = "fourteen ";
                    break;
                case 5:
                    WrittenNumber = "fifteen ";
                    break;
                case 7:
                    WrittenNumber = "seventeen ";
                    break;
                case 6:
                    WrittenNumber = "sixteen ";
                    break;
                case 8:
                    WrittenNumber = "eighteen ";
                    break;
                case 9:
                    WrittenNumber = "nineteen ";
                    break;
                default:
                    WrittenNumber = "";
            }
        }else {
            switch (Edinitsi) {
                case 1:
                    Secondpart = "one ";
                    break;
                case 2:
                    Secondpart = "two ";
                    break;
                case 3:
                    Secondpart = "three ";
                    break;
                case 4:
                    Secondpart = "four ";
                    break;
                case 5:
                    Secondpart = "five ";
                    break;
                case 7:
                    Secondpart = "seven ";
                    break;
                case 6:
                    Secondpart = "six ";
                    break;
                case 8:
                    Secondpart = "eight ";
                    break;
                case 9:
                    Secondpart = "nine ";
                    break;
                default:
                    Secondpart = "";
            }
            switch (Desyatki) {
                case 2:
                    Firstpart = "twenty ";
                    break;
                case 3:
                    Firstpart = "thirty ";
                    break;
                case 4:
                    Firstpart = "forty ";
                    break;
                case 5:
                    Firstpart = "fifty ";
                    break;
                case 6:
                    Firstpart = "sixty ";
                    break;
                case 7:
                    Firstpart = "seventy ";
                    break;
                case 8:
                    Firstpart = "eighty ";
                    break;
                case 9:
                    Firstpart = "ninety ";
                    break;
                default:
                    Firstpart = "";
            }
            WrittenNumber = Firstpart + Secondpart;
        }
        return WrittenNumber;
    }

    private StringBuilder getFirstString(int NumberOfBottles) {
        if (NumberOfBottles >= 10) {
            FirstLine.insert(0, NumberOfBottles).insert(32, NumberOfBottles);
        }
        else {
            FirstLine.insert(0, NumberOfBottles).insert(31, NumberOfBottles);
        }
        if (NumberOfBottles == 1){
            FirstLine.delete(8, 9);
            FirstLine.delete(38, 39);
        }
        return FirstLine;
    }

    private StringBuilder getSecondString(int NumberOfBottles, String WrittenNumber) {
        SecondLine.insert(5, WrittenNumber).insert(30 + WrittenNumber.length() - 3, NumberOfBottles);
        if (NumberOfBottles == 1){
            SecondLine.delete(39 + WrittenNumber.length() - 4, 40 + WrittenNumber.length() - 4);
        }
        return SecondLine;
    }

    private void clearBuilder(int NumberOfBottles, String WrittenNumber, int bottleTakenAtOnce){
        if (NumberOfBottles >= 10) {
            FirstLine.delete(0, 2).delete(30, 32);
        }
        else
        {
            FirstLine.delete(0, 1).delete(30,31);
        }
        if (NumberOfBottles - bottleTakenAtOnce >= 10){
            SecondLine.delete(5, 5 + WrittenNumber.length()).delete(27, 29);
        }
        else
        {
            SecondLine.delete(5, 5 + WrittenNumber.length()).delete(27, 28);
        }
    }
}