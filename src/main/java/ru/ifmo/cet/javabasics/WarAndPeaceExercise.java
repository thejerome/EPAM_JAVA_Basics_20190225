package ru.ifmo.cet.javabasics;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");



        byte[] buf1 = Files.readAllBytes(Paths.get(tome12Path.toString()));
        String str = new String(buf1,"windows-1251");
        byte[] buf2 = Files.readAllBytes(Paths.get(tome34Path.toString()));
        str = str + new String(buf2,"windows-1251");



        Stream<String> wordsOne = Arrays.stream(str.toLowerCase().split("[^a-zа-я]"));
        Stream<String> wordsTwo = Arrays.stream(str.toLowerCase().split("[^a-zа-я]"));

        List<String> uniqueList = wordsOne.distinct().filter(p -> p.length() >= 4).collect(Collectors.toList());
        Stream<String> uniqueStream = uniqueList.stream();
        List<WordTable> uniqueListWordTable = uniqueStream.sorted().map(s -> new WordTable(s)).collect(Collectors.toList());

        List<String> list = wordsTwo.filter(p -> p.length() >= 4).collect(Collectors.toList());
        Stream<String> stream = list.stream();
        List<WordTable> listWordTable = stream.map(s -> new WordTable(s)).collect(Collectors.toList());

        listWordTable.forEach(w -> uniqueListWordTable.get(uniqueListWordTable.indexOf(new WordTable(w.getWord()))).incAmount());

        StringBuilder answer = new StringBuilder("");
        uniqueListWordTable.stream().sorted(new WordTableAmount()).filter(s -> s.getAmount() >= 10).forEach(s -> answer.append(s.getWord() + " - " + s.getAmount() + "\n"));


        return answer.deleteCharAt(answer.length() - 1).toString();

    }

}
class WordTable {
    private String word;
    private int amount;

    public WordTable(String word) {
        this.word = word;
        amount = 0;
    }
    public String getWord(){
        return this.word;
    }
    public void incAmount() {
        this.amount++;
    }
    public int getAmount(){
        return amount;
    }
    @Override
    public boolean equals(Object obj) {
        WordTable objWord = (WordTable)obj;
        return this.word.equals(objWord.word);
    }

}
class WordTableAmount implements Comparator<WordTable> {
    public int compare(WordTable a, WordTable b) {


        return (a.getAmount() > b.getAmount() ? -1 : 0) + (a.getAmount() < b.getAmount() ? 1 : 0);
    }
}