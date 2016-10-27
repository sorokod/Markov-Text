package markov;

import org.junit.Test;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static markov.Chain.NONWORD;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChainTest {

    @Test
    public void build1() throws Exception {

        Iterable<String> iterable = () -> asList("a", "b", "c", "d", "c", "d", "a").iterator();
        Stream<String> tokenStream = stream(iterable.spliterator(), false);

        Chain chain = new Chain(2);
        chain.build(tokenStream);

        assertThat(chain.state.size(), is(7));

        assertThat(chain.state.get(asList("a", "b")), is(asList("c")));
        assertThat(chain.state.get(asList("b", "c")), is(asList("d")));
        assertThat(chain.state.get(asList("c", "d")), is(asList("c", "a")));
        assertThat(chain.state.get(asList("d", "c")), is(asList("d")));
        assertThat(chain.state.get(asList("d", "a")), is(asList(NONWORD)));
        assertThat(chain.state.get(asList(NONWORD, NONWORD)), is(asList("a")));
        assertThat(chain.state.get(asList(NONWORD, "a")), is(asList("b")));

    }

}