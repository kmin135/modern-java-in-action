package chap02.service;

import chap02.dto.Apple;
import chap02.dto.Apple.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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
        Predicate<Apple> redApple = (Apple apple) -> Color.RED.equals(apple.getColor());
        Predicate<Apple> redHeavyApple = redApple.and((Apple a) -> a.getWeight() > 90);
        List<Apple> result = AppleService.filterApplesByPredicate(apples, redHeavyApple);

        // assert
        assertThat(result).contains(a2).doesNotContain(a1);
    }

    @Test
    public void simpleSort() {
        // arrange
        Apple a1 = new Apple(50, Color.GREEN);
        Apple a2 = new Apple(10, Color.RED);
        Apple a3 = new Apple(99, Color.RED);
        Apple a4 = new Apple(99, Color.GREEN);
        List<Apple> apples = Arrays.asList(a1, a2, a3, a4);

//        apples.sort((ap1, ap2) -> ap1.getWeight() - ap2.getWeight());
        apples.sort(Comparator.comparingInt(Apple::getWeight));
        apples.sort(Comparator.comparingInt(Apple::getWeight).thenComparing(Apple::getColor));
//        apples.sort(Comparator.comparingInt(Apple::getWeight).reversed());
        apples.forEach(System.out::println);

        // action
        // assert
    }
}