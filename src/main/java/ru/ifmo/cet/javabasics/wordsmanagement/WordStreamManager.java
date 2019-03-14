package ru.ifmo.cet.javabasics.wordsmanagement;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface WordStreamManager<T> {

    /**
     * Get a Stream of words from the given file
     * @param path path to the file
     * @param minLength inimum length of a word in final Stream
     * @return Stream of words
     */
    public Stream<T> getStream(Path path, int minLength);

    default public Stream<T> getStream(Path path){
        return getStream(path,0);
    }
}
