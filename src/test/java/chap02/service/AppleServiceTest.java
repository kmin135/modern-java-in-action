package chap02.service;

import chap02.dto.Apple;
import chap02.dto.Apple.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class AppleServiceTest {
    AppleService svc = new AppleService();

    @Test
    public void filterGreenApples() {
        // arrange
        Apple a1 = new Apple(100, Color.GREEN);
        Apple a2 = new Apple(100, Color.RED);
        List<Apple> apples = Arrays.asList(a1, a2);

        // action
        List<Apple> result = AppleService.filterApplesByColor(apples, Color.GREEN);

        // assert
        assertThat(result).contains(a1).doesNotContain(a2);
    }

    @Test
    public void filterPredicate() {
        // arrange
        Apple a1 = new Apple(100, Color.GREEN);
        Apple a2 = new Apple(100, Color.RED);
        List<Apple> apples = Arrays.asList(a1, a2);

        // action
        List<Apple> result = AppleService.filterApplesByPredicate(apples, (Apple apple) -> Color.RED.equals(apple.getColor()));

        // assert
        assertThat(result).contains(a2).doesNotContain(a1);
    }
}