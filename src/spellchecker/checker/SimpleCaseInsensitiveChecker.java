package spellchecker.checker;

import spellchecker.dictionary.Dictionary;

import java.util.HashSet;
import java.util.stream.Collectors;

public class SimpleCaseInsensitiveChecker implements Checker {
    private HashSet<String> knownWords;

    @Override
    public boolean isValid(String word) {
        return knownWords.contains(word.toLowerCase());
    }

    public SimpleCaseInsensitiveChecker(Dictionary dictionary) {
        this.knownWords = dictionary.getWords()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
