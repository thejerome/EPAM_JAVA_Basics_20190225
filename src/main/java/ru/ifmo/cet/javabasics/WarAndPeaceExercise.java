package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WarAndPeaceExercise{
    private static List<String> DiffWords = new ArrayList<>();
    private static List<String> Words = new ArrayList<>();
    private static List<Integer> Currency = new ArrayList<>();
    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main","resources","WAP12.txt");
        final Path tome34Path = Paths.get("src", "main","resources","WAP34.txt");
        readFile(tome12Path);
        readFile(tome34Path);
        countWordFrequency();
        addToStorage();
        Storage.sortPairs();
        return Storage.getResult();
    }

    private static void addToStorage(){
        for (int i = 0; i < DiffWords.size(); i++) {
            if (Currency.get(i) >= 10) {
                new Storage(DiffWords.get(i), Currency.get(i));
            }
        }
    }

    private static void readFile(Path tome) {
        try {
            BufferedReader WAP1 = new BufferedReader(new InputStreamReader(new FileInputStream(tome.toString()), Charset.forName("Windows-1251")));
            String Word = "";
            while ((Word = WAP1.readLine()) != null){
                Word = Word.toLowerCase();
                for (String FinalWord : Word.split("[^а-яa-z]")){
                    if (FinalWord.length() >= 4) {
                        Words.add(FinalWord);
                        checkUnique(FinalWord);
                    }
                }
            }
        }
        catch (IOException a) {
            a.printStackTrace();
        }
    }

    private static void checkUnique(String Word){
        boolean foundWord = false;
        for (int i = 0; i < DiffWords.size(); i++) {
            if (Word.equals(DiffWords.get(i))) {
                foundWord = true;
            }
        }
        if (!foundWord){
            DiffWords.add(Word);
        }
    }

    private static void countWordFrequency() {
        int count = 0;
        for (int i = 0; i < DiffWords.size(); i++) {
            for (int j = 0; j < Words.size(); j++) {
                if (Words.get(j).equals(DiffWords.get(i))){
                    count++;
                }
            }
            Currency.add(count);
            count = 0;
        }
    }
}