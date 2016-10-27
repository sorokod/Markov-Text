package markov;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Chain {

    static final String NONWORD = "\u05D0";

    final HashMap<List<String>, List<String>> state = new HashMap<>();
    private final Prefixes factory;
    private List<String> prefix;


    public Chain(int prefixSize) {
        this.factory = new Prefixes(prefixSize, NONWORD);
        this.prefix = factory.getInitial();
    }


    public void build(Stream<String> tokenStream) throws IOException {
        tokenStream.forEach(token -> add(token));
        add(NONWORD);
    }


    private void add(String token) {
        state.computeIfAbsent(prefix, p -> new ArrayList<>()).add(token);
        prefix = factory.getUpdated(prefix, token);
    }


    public void generate(int nwords) {
        Random rand = new Random();

        prefix = factory.getInitial();

        for (int i = 0; i < nwords; i++) {
            List<String> s = state.get(prefix);

            int r = rand.nextInt(s.size());
            String suf = s.get(r);

            if (suf.equals(NONWORD)) {
                break;
            }

            System.out.println(suf);
            prefix = factory.getUpdated(prefix, suf);
        }
    }


}