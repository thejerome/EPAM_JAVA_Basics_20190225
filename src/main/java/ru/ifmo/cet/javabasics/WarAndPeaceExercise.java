package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        Map<String, Integer> wordMap = buildWordMap(tome12Path, tome34Path);
        Map<String, Integer> sortedWordMap = sortByValue(wordMap);

        return calculateWordCount(sortedWordMap);
    }

    public static Map<String, Integer> buildWordMap(Path... paths) {

        Map<String, Integer> result = new HashMap<>();
        final String CHARSET = "windows-1251";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new SequenceInputStream
                (new FileInputStream(paths[0].toFile()), new FileInputStream(paths[1].toFile())), Charset.forName(CHARSET)))) {

            String line = null;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("[^a-zA-Zа-яА-ЯёЁ]");
                for (String token : tokens) {
                    if (token.length() >= 4)
                        if (result.get(token.toLowerCase()) == null)
                            result.put(token.toLowerCase(), 1);
                        else result.put(token.toLowerCase(), result.get(token.toLowerCase()) + 1);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static String calculateWordCount(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 10) {
                sb.append(String.format("%s - %d\n", entry.getKey(), entry.getValue()));
            }
        }
        return sb.toString().trim();
    }
}
