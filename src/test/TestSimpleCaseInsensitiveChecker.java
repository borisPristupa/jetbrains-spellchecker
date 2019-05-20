package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spellchecker.checker.Checker;
import spellchecker.checker.SimpleCaseInsensitiveChecker;
import spellchecker.dictionary.FileDictionaryLoader;

class TestSimpleCaseInsensitiveChecker {
    private static Checker checker;

    @BeforeAll
    static void init() throws Exception {
        checker = new SimpleCaseInsensitiveChecker(new FileDictionaryLoader().load(
                FileDictionaryLoader.localFileURI("res/litw-win.txt")
        ));
    }

    @Test
    void testContains() {
        Assertions.assertTrue(checker.isValid("привет"));
        Assertions.assertTrue(checker.isValid("как"));
        Assertions.assertTrue(checker.isValid("дела"));
    }

    @Test
    void testNotContains() {
        Assertions.assertFalse(checker.isValid("превед"));
//        Assertions.assertFalse(checker.isValid("медвед")); Оказывается, такое слово есть

//        Assertions.assertFalse(checker.isValid("ихний")); кто бы мог подумать, но такое слово тоже есть

        Assertions.assertFalse(checker.isValid("победю"));
        Assertions.assertFalse(checker.isValid("дикси"));
    }
}
