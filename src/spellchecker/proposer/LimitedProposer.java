package spellchecker.proposer;

import java.util.Collection;

public abstract class LimitedProposer implements Proposer {
    public abstract Collection<String> proposeValid(String word, int amount);

    @Override
    public Collection<String> proposeValid(String word) {
        return proposeValid(word, Integer.MAX_VALUE);
    }
}
