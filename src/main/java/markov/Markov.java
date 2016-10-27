package markov;

import java.io.IOException;
import java.util.stream.Stream;

class Markov {
    static final int MAX_TOKENS = 1000;

    public static void main(String[] args) throws IOException {
        try (Stream<String> in = new FileTokenStream("src/main/resources/beowulf.txt").stream()) {
//        try (Stream<String> in = new FileTokenStream(args[0]).stream()) {

            Chain chain = new Chain(2);

            chain.build(in);
            chain.generate(MAX_TOKENS);
        }
    }
}