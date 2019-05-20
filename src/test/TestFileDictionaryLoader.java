package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spellchecker.dictionary.FileDictionaryLoader;

import java.util.Set;
import java.util.TreeSet;

class TestFileDictionaryLoader {

    private static Set<String> words;

    @Test
    @BeforeAll
    static void testDictionaryLoads() {
        Assertions.assertDoesNotThrow(() -> {
            words = new FileDictionaryLoader().load(
                    FileDictionaryLoader.localFileURI("res/litw-win.txt")
            ).getWords();
        });
    }

    @Test
    void testNumberOfWords() {
        Assertions.assertEquals(words.size(), 162164);
    }

    @Test
    void testLastBySortWord() {
        Assertions.assertEquals("ящичком",
                                new TreeSet<>(words).last());
    }

    @Test
    void testLargestWord() {
        String largesWord = "";
        for (String s : words)
            largesWord = s.length() > largesWord.length() ? s : largesWord;

        Assertions.assertEquals(largesWord, "попреблагорассмотрительствующемуся");

    }
}
