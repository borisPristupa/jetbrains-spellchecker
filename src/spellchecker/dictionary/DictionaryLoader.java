package spellchecker.dictionary;

import java.net.URI;

public interface DictionaryLoader {
    Dictionary load(URI dictionaryUri) throws Exception;
}
