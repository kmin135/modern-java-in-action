package chap06;

import common.Dish;
import common.Dish.TYPE;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ReducingAndSummaryTest {
    List<Dish> dishes = null;
    @BeforeEach
    void setup() {
        dishes = Arrays.asList(
                Dish.builder().name("pork").calories(800).type(TYPE.MEAT).build(),
                Dish.builder().name("beef").calories(700).type(TYPE.MEAT).build(),
                Dish.builder().name("salmon").calories(450).type(TYPE.FISH).build());
    }

    @Test
    void calculateCalories() {
        Integer totalCalories = dishes.stream().collect(summingInt(d -> d.getCalories()));
        Assertions.assertThat(totalCalories).isEqualTo(1950);

        Double avg = dishes.stream().collect(averagingDouble(Dish::getCalories));
        Assertions.assertThat(avg).isBetween(649D, 651D);
    }

    @Test
    void joiningName() {
        String joined = dishes.stream().map(Dish::getName).collect(joining(" "));
        Assertions.assertThat(joined).isEqualTo("pork beef salmon");
    }
}
