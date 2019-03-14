package ru.ifmo.cet.javabasics.wordsmanagement;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Builds a Stream, all words are in lower case
 */
public class LowerCasedWordStreamManager implements WordStreamManager<String> {

    @Override
    public Stream<String> getStream(Path path, int minLength) {
        try{
            return Files.lines(path, Charset.forName("Windows-1251"))
                    .parallel()
                    .flatMap(s-> Stream.of(s.split("[^a-zA-Zа-яА-Я]")))
                    .filter(s -> (s.length() > 3))
                    .map(String::toLowerCase);
        } catch (IOException ex){
            return Stream.empty();
        }
    }
}
