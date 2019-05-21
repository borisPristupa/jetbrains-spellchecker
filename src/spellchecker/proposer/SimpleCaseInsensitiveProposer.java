package spellchecker.proposer;

import spellchecker.dictionary.Dictionary;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// not concurrent
public class SimpleCaseInsensitiveProposer extends LimitedProposer {
    private Collection<String> knownWords;

    @Override
    public Collection<String> proposeValid(String word, int amount) {
        if (0 == amount) return Collections.emptyList();

        TreeMap<Integer, Collection<String>> answer = new TreeMap<>();
        AtomicInteger size = new AtomicInteger();

        knownWords.forEach(s -> {
            int distance = DamerauLevenshtein.distance(word, s);

            if (!answer.containsKey(distance)) {
                answer.put(distance, new ArrayList<>());
            }
            answer.get(distance).add(s);
            if (size.incrementAndGet() > amount) {
                Iterator<String> iterator = answer.get(answer.lastKey()).iterator();
                iterator.next();
                iterator.remove();
                if (!iterator.hasNext()) answer.remove(answer.lastKey());
            }
        });

        return answer.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public SimpleCaseInsensitiveProposer(Dictionary dictionary) {
        knownWords = dictionary.getWords()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
