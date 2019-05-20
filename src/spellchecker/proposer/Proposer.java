package spellchecker.proposer;

import java.util.Collection;

public interface Proposer {
    Collection<String> proposeValid(String word);
}
