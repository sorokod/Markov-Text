package markov;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileTokenStream implements AutoCloseable {

    private final Stream<String> stream;


    public FileTokenStream(String file) throws FileNotFoundException {
        InputStream is = new FileInputStream(new File(file));
        Iterable<String> iterable = () -> new Scanner(is);

        stream = StreamSupport.stream(iterable.spliterator(), false);
    }


    public Stream<String> stream() {
        return stream;
    }


    @Override
    public void close() throws IOException {
        stream.close();
    }
}
