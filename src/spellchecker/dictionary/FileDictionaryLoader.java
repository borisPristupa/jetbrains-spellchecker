package spellchecker.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.stream.Collectors;

public class FileDictionaryLoader implements DictionaryLoader {
    @Override
    public Dictionary load(URI dictionaryUri) throws IOException {
        Path dicPath = Paths.get(dictionaryUri);
        if (Files.notExists(dicPath)) throw new NoSuchFileException("File " + dicPath + " does not " +
                                                                            "exist. Files.notExists(Path path," +
                                                                            "LinkOption... options) returned " +
                                                                            "true");
        if (!Files.exists(dicPath)) throw new AccessDeniedException("No access to path " + dicPath + "," +
                                                                            " both Files.exists(Path path," +
                                                                            "LinkOption... options) and " +
                                                                            "Files.notExists(Path path," +
                                                                            "LinkOption... options) returned " +
                                                                            "false");
        File dicFile = dicPath.toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(dicFile))) {
            return new Dictionary(
                    reader.lines()
                            .map(String::trim)
                            .collect(Collectors.toSet())
            );
        }
    }

    public static URI localFileURI(String path) {
        return Paths.get(path).toUri();
    }
}
