package chap06;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitioningTest {
    private static boolean isPrime(int candidate) {
        int candidateRoot = (int)Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    @Test
    void partitionPrimes() {
        Map<Boolean, List<Integer>> collect = IntStream.range(2, 20).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));

        System.out.println("소수 리스트: " + collect.get(true).stream().map(i -> String.valueOf(i)).collect(Collectors.joining(", ")));
        System.out.println("소수가 아닌 리스트: " + collect.get(false).stream().map(i -> String.valueOf(i)).collect(Collectors.joining(", ")));
    }
}
