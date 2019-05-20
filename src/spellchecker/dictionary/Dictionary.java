package spellchecker.dictionary;

import java.util.Set;
import java.util.TreeSet;

public class Dictionary {
    private Set<String> words;

    public Set<String> getWords() {
        return new TreeSet<>(words);
    }

    public Dictionary(Set<String> words) {
        this.words = new TreeSet<>(words);
    }
}
