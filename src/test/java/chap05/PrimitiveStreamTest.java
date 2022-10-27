package chap05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class PrimitiveStreamTest {
    @Test
    void range() {
        long count = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).count();
        Assertions.assertThat(count).isEqualTo(50);
    }
}
