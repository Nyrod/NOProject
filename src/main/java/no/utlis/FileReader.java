package no.utlis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public static String readFile(String path) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1);
        stream.forEach(s -> fileContent.append(s + "\n"));

        return fileContent.toString();
    }
}
