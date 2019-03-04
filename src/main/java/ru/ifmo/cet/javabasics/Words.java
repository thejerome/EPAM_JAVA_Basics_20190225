package ru.ifmo.cet.javabasics;

class Words implements Comparable {
    String word;
    Integer amount;

    public Words(String word, Integer amount) {
        this.word = word;
        this.amount = amount;
    }

    public String getWord() {
        return word;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Object o) {
        Words words = (Words) o;
        int result = words.amount - this.amount;
        return result == 0 ?  this.word.compareTo(words.word) : result;
    }

    @Override
    public String toString() {
        return word + " - " + amount + "\n";
    }
}
