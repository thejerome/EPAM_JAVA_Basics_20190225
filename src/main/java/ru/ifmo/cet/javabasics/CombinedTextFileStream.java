package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CombinedTextFileStream {
    private final Path[] paths;
    private final Charset charset;

    public CombinedTextFileStream(Charset charset, Path... paths) {
        this.paths = paths;
        this.charset = charset;
    }

    public Stream<String> getCombinedStream() {
        Stream<String> combinedStream = Stream.empty();

        for (Path path : paths) {
            try {
                combinedStream = Stream.concat(combinedStream, Files.lines(path, charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return combinedStream;
    }
}
