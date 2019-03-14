package ru.ifmo.cet.javabasics;

import ru.ifmo.cet.javabasics.wordsmanagement.LowerCasedWordStreamManager;
import ru.ifmo.cet.javabasics.wordsmanagement.WordStreamManager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class WarAndPeaceExercise {
    /**
     * Build a string of words' frequencies
     * @param dictionary map of word frequency dictionary
     * @param minWordCount minimum count of words frequency to be added in a result string
     * @param delimiter chars sequence to separate a word and it's frequency
     * @return result in a string, words' characteristics are in separated lines
     */
    public static String buildDictionaryOverview(Map <String, Integer> dictionary, int minWordCount, String delimiter){
        return dictionary.entrySet()
                .parallelStream()
                .filter(e -> (e.getValue()>=minWordCount))
                .sorted((o1, o2) -> // to much ternary, but test isn't not loyal to ifstatement
                        o1.getValue()<o2.getValue()
                                ? 1
                                : o1.getValue()>o2.getValue()
                                ? -1
                                : o1.getKey().compareTo(o2.getKey()))
                .map(e -> e.getKey()+delimiter+e.getValue())
                .collect(Collectors.joining("\n","",""));
    }

    public static String warAndPeace() {
        final int wordMinLength = 4;
        final int minWordCount  = 10;
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        WordStreamManager<String> builder = new LowerCasedWordStreamManager();
        Map<String, Integer> dictionary =  Stream.concat(builder.getStream(tome12Path,wordMinLength),
                                                         builder.getStream(tome34Path,wordMinLength))
                                                         .collect(groupingBy(Function.identity(), summingInt(e -> 1)));

        return buildDictionaryOverview(dictionary,minWordCount," - ");

    }
}