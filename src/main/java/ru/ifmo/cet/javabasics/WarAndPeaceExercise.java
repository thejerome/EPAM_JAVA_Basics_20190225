package ru.ifmo.cet.javabasics;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;




public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {

        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");


        Scanner scannerFile1 = new Scanner(tome12Path, "windows-1251");

        boolean flag = true;
        Comparator<WordTable> comp = new WordTableAmount().thenComparing(new WordTableWord());
        ArrayList<WordTable> list = new ArrayList<WordTable>();
        while (scannerFile1.hasNext()) {
            String line;
            line = scannerFile1.nextLine().toLowerCase();
            String[] words = line.split("[^a-zа-я]");
            for (String word : words) {

                    if (!list.contains(new WordTable(word))) {
                        if (word.length() >= 4) {
                            list.add(new WordTable(word, 1));
                        }
                    } else {
                        int wordIndex = list.indexOf(new WordTable(word));
                        WordTable newWord = new WordTable(list.get(wordIndex).getWord(), list.get(wordIndex).incAmount());
                        list.set(wordIndex, newWord);
                    }

            }

            if (!scannerFile1.hasNext() && flag) {
                flag = false;
                scannerFile1 = new Scanner(tome34Path, "windows-1251");
            }
        }
        list.sort(comp);
        String answer = "";
        for (WordTable element: list) {
            if (element.getAmount() < 10) {
                break;
            }

            answer = answer + element.getWord() + " - " + Integer.toString(element.getAmount()) + "\n";
        }
        return answer.substring(0, answer.length()-1);

    }

}

class WordTable  {

    String word;
    int amount;

    public WordTable(String w, int a) {
        word = w;
        amount = a;
    }
    public WordTable(String w) {
        word = w;
    }
    public WordTable(WordTable w) {
        this.word = w.word;
    }
    String getWord() {
        return word;
    }
    int getAmount() {
        return amount;
    }

    int incAmount() {
        return (amount + 1);
    }
    @Override
    public boolean equals(Object obj) {
        WordTable objWord = (WordTable)obj;
        return this.word.equals(objWord.word);
    }
}

class WordTableWord implements Comparator<WordTable> {
    public int compare(WordTable a, WordTable b) {
        return a.getWord().compareTo(b.getWord());
    }
}

class WordTableAmount implements Comparator<WordTable> {
    public int compare(WordTable a, WordTable b) {
        if (a.getAmount() > b.getAmount()) {
            return -1;
        } else if (a.getAmount() == b.getAmount()) {
            return 0;
        } else {
            return 1;
        }
    }
}