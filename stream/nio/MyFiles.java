package stream.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MyFiles {
    public static void main(String[] args) throws IOException {
        /** Найти все уникальные слова в файле */
        Path path = Paths.get("/Users/fisher/IdeaProjects/functional/stream/nio/data.txt");

        long uniqueWords = Files.lines(path, Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();

        System.out.println(uniqueWords);
    }
}
