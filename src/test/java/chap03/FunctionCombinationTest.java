package chap03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionCombinationTest {

    @Test
    void functionCombination() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        // f(g(x))
        Function<Integer, Integer> fg = f.andThen(g);

        Assertions.assertThat(fg.apply(5)).isEqualTo(12);

        // g(f(x))
        Function<Integer, Integer> gf = f.compose(g);
        Assertions.assertThat(gf.apply(5)).isEqualTo(11);

    }

}
