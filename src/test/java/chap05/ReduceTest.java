package chap05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;

public class ReduceTest {
    @Test
    void sum() {
        int[] nums = {1,2,3,4};

        // 초기값이 있으므로 결과가 항상 있기 때문에 바로 int 결과 리턴
        int result1 = Arrays.stream(nums).reduce(0, (a, b) -> a + b);
        Assertions.assertThat(result1).isEqualTo(10);

        // 초기값이 없음. 이 때는 스트림마저 비어있으면 결과가 존재하지 않으므로 Optional 로 결과 리턴
        OptionalInt reduce = Arrays.stream(nums).reduce(Integer::sum);
        int result2 = reduce.orElse(-1);
        Assertions.assertThat(result2).isEqualTo(10);
    }

    @Test
    void max() {
        int[] nums = {7,2,6,2,9};

        OptionalInt reduce = Arrays.stream(nums).reduce(Integer::max);
        Assertions.assertThat(reduce.orElse(-1)).isEqualTo(9);
    }
}
