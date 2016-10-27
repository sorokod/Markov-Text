package markov;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.nCopies;

class Prefixes {

    private final int prefixSize;
    private final String noword;

    Prefixes(int prefixSize, String noword) {
        this.prefixSize = prefixSize;
        this.noword = noword;
    }


    public List<String> getInitial() {
        return nCopies(prefixSize, noword);
    }


    public List<String> getUpdated(List<String> prev, String token) {
        List<String> updated = new ArrayList<>(prev);
        updated.remove(0);
        updated.add(token);
        return updated;
    }


}
