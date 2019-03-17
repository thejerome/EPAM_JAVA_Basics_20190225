package ru.ifmo.cet.javabasics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Storage{
    private static List<Storage> Refs = new ArrayList<>();
    private String Word;
    private int Value;
    public Storage(String OneWord, int count) {
        this.Value = count;
        this.Word = OneWord;
        Refs.add(this);
    }

    public static String getResult(){
        StringBuilder Res = new StringBuilder();
        for (int i = 0; i < Refs.size() - 1; i++) {
                    Res.append(Refs.get(i).Word).append(" - ").append(Refs.get(i).Value).append("\n");
        }
        Res.append(Refs.get(Refs.size() - 1).Word).append(" - ").append(Refs.get(Refs.size() - 1).Value);
        return Res.toString();
    }

    public static void sortPairs(){
        Collections.sort(Refs, Storage.getComp());
    }

    public static Comparator<Storage> getComp() {
        Comparator comp = new Comparator<Storage>() {
            public int compare(Storage storage, Storage t1) {
                if (storage.Value < t1.Value) {
                    return 1;
                }
                if (storage.Value > t1.Value) {
                    return -1;
                }
                if (t1.Value == storage.Value) {
                    return storage.Word.compareTo(t1.Word);
                }
                return 0;
            }
        };
        return comp;
    }
}
