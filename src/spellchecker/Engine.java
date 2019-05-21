package spellchecker;

import spellchecker.checker.Checker;
import spellchecker.checker.SimpleCaseInsensitiveChecker;
import spellchecker.dictionary.Dictionary;
import spellchecker.dictionary.FileDictionaryLoader;
import spellchecker.proposer.LimitedProposer;
import spellchecker.proposer.SimpleCaseInsensitiveProposer;
import spellchecker.ui.ConsoleWordReader;
import spellchecker.ui.WordReader;

import java.io.IOException;

public class Engine {
    private Checker checker;
    private LimitedProposer proposer;

    private Dictionary loadDict() throws IOException {
        return new FileDictionaryLoader().load(
                FileDictionaryLoader.localFileURI("res/litw-win.txt")
        );
    }

    private void init() throws IOException {
        Dictionary rusLitDict = loadDict();
        checker = new SimpleCaseInsensitiveChecker(rusLitDict);
        proposer = new SimpleCaseInsensitiveProposer(rusLitDict);
    }

    public void start() throws Exception {
        try {
            init();
        } catch (IOException e) {
            throw new Exception("Init failed", e);
        }

        System.out.println("Приветствую! Вводите слово, а я его проверю на соответствие лексикону " +
                                   "русских прозаиков и поэтов XIX-XX веков. Если надоест, введите exit");
        WordReader reader = new ConsoleWordReader();
        String word;
        while (true) {
            try {
                word = reader.readWord();
                if ("exit".equals(word)) break;

                if (checker.isValid(word)) {
                    System.out.println("Всё точно! А вы случайно не поэт?");
                } else {
                    System.out.println("Кажется, это слово почти не использовалось писателями двух прошлых веков");
                    System.out.println("А может, вы имели в виду что-то из нижеперечисленного?");
                    proposer.proposeValid(word, 5).forEach(System.out::println);
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("К моему глубочайшему сожалению, что-то пошло не так и я сломался");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new Engine().start();
    }
}
