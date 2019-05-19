package spellchecker.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleWordReader implements WordReader {
    @Override
    public String readWord() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words;
        do {
            words = Stream.of(
                    reader.readLine().trim().split("[^a-zA-Zа-яА-ЯёЁ]")
            ).filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        } while (words.isEmpty());

        if (words.size() > 1)
            System.out.println("I read only one word: " + words.get(0));

        return words.get(0);
    }
}
