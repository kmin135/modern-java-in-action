package chap07;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class SimpleParallelTest {

    @Test
    void oldIterateSum() {
        long sum = 0L;
        for(int i=1;i<=10;i++) {
            sum += i;
        }
        Assertions.assertThat(sum).isEqualTo(55L);
    }

    @Test
    void sequentialSum() {
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(10)
                .reduce(0L, Long::sum);
        Assertions.assertThat(sum).isEqualTo(55L);
    }

    @Test
    void parallelSum() {
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(10)
                .parallel()
                .reduce(0L, Long::sum);
        Assertions.assertThat(sum).isEqualTo(55L);
    }
}
