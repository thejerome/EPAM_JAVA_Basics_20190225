package ru.ifmo.cet.javabasics;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        StringBuilder a = new StringBuilder();
        a.append(StreamPath(tome12Path, tome34Path));
        return a.toString();
    }

    private static String StreamPath(Path tome, Path tome2){
        String a = "";
        try {
            String words = Stream.concat(
                    Files.lines(tome, Charset.forName("Windows-1251")),
                    Files.lines(tome2, Charset.forName("Windows-1251")))
            .collect(Collectors.joining(" "));
            a = asList(requireNonNull(words).toLowerCase().split("[^\\p{L}]"))
                    .stream()
                    .filter(s -> s.length() >= 4)
                    .collect(groupingBy(s -> s, counting()))
                    .entrySet().stream().filter(m -> m.getValue() >= 10)
                    .sorted((e0, e1) -> {
                        final int res = e1.getValue().compareTo(e0.getValue());
                        return res == 0 ? e0.getKey().compareTo(e1.getKey()) : res;
                    }
            )
                    .map(m -> m.getKey() + " - " + m.getValue())
                    .collect(Collectors.joining("\n"));
            return a;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

}