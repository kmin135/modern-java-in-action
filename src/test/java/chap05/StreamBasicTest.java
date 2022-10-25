package chap05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasicTest {

    @Test
    @DisplayName("중복없는 짝수 추출")
    void evenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        List<Integer> collect = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertThat(collect).containsExactlyInAnyOrder(2, 4);
    }
    @Test
    @DisplayName("문자열 리스트에서 고유 문자 리스트 반환")
    void simpleFlat() {
        String[] words = {"Hello", "World"};

        /*
        // 그냥 map을 쓰면 Stream<Stream<String>> 가 되버린다.
        Arrays.stream(words)
                .map(word -> word.split(""))
                .map(Arrays::stream);
        */

        List<String> collect = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertThat(collect).containsExactly("H", "e", "l", "o", "W", "r", "d");
    }

    @Test
    @DisplayName("제곱근 구하기")
    void mapQuiz1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = numbers.stream().map(i -> i * i)
                .collect(Collectors.toList());

        Assertions.assertThat(collect).containsExactly(1, 4, 9, 16, 25);
    }

    @Test
    @DisplayName("두 개의 숫자 리스트에서 모든 숫자 쌍의 리스트 얻기")
    void mapQuiz2() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        List<int[]> collect = list1.stream()
                .flatMap(i -> list2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        Assertions.assertThat(collect).containsExactly(
                new int[]{1,3}, new int[]{1,4},
                new int[]{2,3}, new int[]{2,4},
                new int[]{3,3}, new int[]{3,4});
    }
}
