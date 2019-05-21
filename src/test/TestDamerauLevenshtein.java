package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spellchecker.proposer.DamerauLevenshtein;

class TestDamerauLevenshtein {

    @Test
    void testEquals() {
        Assertions.assertEquals(0, DamerauLevenshtein.distance("hello", "hello"));
        Assertions.assertEquals(0, DamerauLevenshtein.distance("", ""));
    }

    @Test
    void testInsert() {
        Assertions.assertEquals(1, DamerauLevenshtein.distance("Worl", "World"));
        Assertions.assertEquals(1, DamerauLevenshtein.distance("World", "Worl"));

        Assertions.assertEquals(3, DamerauLevenshtein.distance("сол", "солнце"));
        Assertions.assertEquals(3, DamerauLevenshtein.distance("солнце", "сол"));

        Assertions.assertEquals(2, DamerauLevenshtein.distance("посол", "сол"));
        Assertions.assertEquals(2, DamerauLevenshtein.distance("сол", "посол"));

        Assertions.assertEquals(5, DamerauLevenshtein.distance("", "World"));
        Assertions.assertEquals(5, DamerauLevenshtein.distance("World", ""));
    }

    @Test
    void testReplace() {
        Assertions.assertEquals(1, DamerauLevenshtein.distance("Worl", "Worm"));
        Assertions.assertEquals(1, DamerauLevenshtein.distance("Worm", "Worl"));

        Assertions.assertEquals(1, DamerauLevenshtein.distance("Fork", "Pork"));
        Assertions.assertEquals(1, DamerauLevenshtein.distance("Pork", "Fork"));

        Assertions.assertEquals(23, DamerauLevenshtein.distance("Internship at JetBrains",
                                                               "Это интересные проекты!"));
        Assertions.assertEquals(23, DamerauLevenshtein.distance("Это интересные проекты!",
                                                               "Internship at JetBrains"));
    }

    @Test
    void testTransition() {
        Assertions.assertEquals(1, DamerauLevenshtein.distance("12", "21"));
        Assertions.assertEquals(1, DamerauLevenshtein.distance("21", "12"));

        Assertions.assertEquals(1, DamerauLevenshtein.distance("123", "132"));
        Assertions.assertEquals(1, DamerauLevenshtein.distance("123", "213"));

        Assertions.assertEquals(2, DamerauLevenshtein.distance("1234", "2143"));
        Assertions.assertEquals(2, DamerauLevenshtein.distance("1234", "3124"));
    }

    @Test
    void testCombined() {
        Assertions.assertEquals(3, DamerauLevenshtein.distance("осёл", "сопля"));
        Assertions.assertEquals(3, DamerauLevenshtein.distance("сопля", "осёл"));
    }
}
