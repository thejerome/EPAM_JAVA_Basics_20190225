package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;


public class WarAndPeaceExercise {





    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //private List<String> words = new ArrayList;

        Map<String,Integer> words = new HashMap();
        readVolume(tome12Path, words);
        readVolume(tome34Path);
        String wordsCounted = toString(words);
        System.out.println();
        return wordsCounted;
        //throw new UnsupportedOperationException();
    }
    private void readVolume(String path, Map<String, Integer> words){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.toLowerCase().split("\\W");
                for(String str:lines){
                    if(words.containsKey(str)){
                        words.put(str, words.get(str) + 1);
                    }else{
                        words.put(str,1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Map<String, Integer> splitAndCollect(String line){
        //String[] lines = line.toLowerCase().split("[\\d\\s]");

        String[] lines = line.toLowerCase().split("\\W");
        for (String str :lines) {

        }
    }
    private String toString(Map<String, Integer> words){
        StringBuffer sb = new StringBuffer("");

        Set<Entry<String, Integer>> entries = words.entrySet();
        for(Map.Entry<String, Integer> entry : entries){
            sb.append( entry.getKey() + " - "  entry.getValue() + "\n");
        }
        return sb.toString();
    }
}