package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spellchecker.dictionary.FileDictionaryLoader;
import spellchecker.proposer.LimitedProposer;
import spellchecker.proposer.SimpleCaseInsensitiveProposer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

class TestSimpleCaseInsensitiveProposer {
    private static LimitedProposer proposer;

    @BeforeAll
    static void testDictionaryLoads() throws IOException {
        proposer = new SimpleCaseInsensitiveProposer(
                new FileDictionaryLoader().load(
                        FileDictionaryLoader.localFileURI("res/litw-win.txt"))
        );
    }

    @Test
    void recommendationSize() {
        for (int i = 0; i < 15; i++) {
            Assertions.assertEquals(i,
                                    proposer.proposeValid(
                                            Double.toString(new Random().nextDouble()),
                                            i

                                    ).size()
            );
        }
    }

    @Test
    void someKnownRecommendations() {
        Assertions.assertTrue(proposer.proposeValid("поход", 20).containsAll(
                Arrays.asList("походе", "походы", "походя", "походи", "повод", "пород", "доход", "проход", "подход")
        ));
    }
}
