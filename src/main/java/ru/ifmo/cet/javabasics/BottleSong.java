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

    private final int bottleTakenAtOnce;
    private int bottleCount = 99;
    public BottleSong(int bottleTakenAtOnce) {
        if(bottleTakenAtOnce < 1 || bottleTakenAtOnce > 99){
            throw new IllegalArgumentException();
        }
        this.bottleTakenAtOnce = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        StringBuffer sb = new StringBuffer();
        int erase = 0;
        while (bottleCount > 0) {
            sb.append(bottleCount + bottlesOnWall(bottleCount)+ ", "+
                    bottleCount + bottlesOfBeer(bottleCount) + ".\n");
            erase = bottleCount > bottleTakenAtOnce ? bottleTakenAtOnce : bottleCount;
            bottleCount -= erase;
            if(bottleCount>0) {
                sb.append("Take " + numToString(erase) + "down and pass around, " +
                        bottleCount + bottlesOnWall(bottleCount)+".\n");
            }else{
                sb.append("Take " + numToString(erase) + "down and pass around, " +
                        "no more" + bottlesOnWall(bottleCount)+".\n");
            }
        }
        sb.append("No more bottles of beer on the wall, no more bottles of beer.\n" +
                  "Go to the store and buy some more, 99 bottles of beer on the wall.\n");

        return sb.toString();
    }
    private String numToString(int i){
        if( i > 9 && i < 20){
            switch(i){
                case 10: return "ten ";
                case 11: return "eleven ";
                case 12: return "twelve ";
                case 13: return "thirteen ";
                case 14: return "fourteen ";
                case 15: return "fifteen ";
                case 16: return "sixteen ";
                case 17: return "seventeen ";
                case 18: return "eighteen ";
                case 19: return "nineteen ";
                default: return "Codacy want to put default in this switch";
            }
        }
        String tens = cntTens(i);
        String ones = cntOnes(i);
        return tens + ones;
    }
    private String bottlesOnWall(int i){
        if(i!=1){
            return " bottles of beer on the wall";
        }
        return " bottle of beer on the wall";
    }
    private String bottlesOfBeer(int i){
        if(i!=1){
            return " bottles of beer";
        }
        return " bottle of beer";
    }
    private String cntTens(int i){
        String tens;
        switch(i/10){
            case 2: tens = "twenty "; break;
            case 3: tens = "thirty "; break;
            case 4: tens = "forty "; break;
            case 5: tens = "fifty "; break;
            case 6: tens = "sixty "; break;
            case 7: tens = "seventy "; break;
            case 8: tens = "eighty "; break;
            case 9: tens = "ninety "; break;
            default: tens = "";break;
        }
        return tens;
    }
    private String cntOnes(int i){
        String ones;
        switch(i%10){
            case 1: ones ="one ";break;
            case 2: ones ="two ";break;
            case 3: ones = "three ";break;
            case 4: ones = "four "; break;
            case 5: ones = "five ";break;
            case 6: ones = "six ";break;
            case 7: ones = "seven "; break;
            case 8: ones = "eight ";break;
            case 9: ones = "nine ";break;
            default: ones = "";break;
        }
        return ones;
    }
}
