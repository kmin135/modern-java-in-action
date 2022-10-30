package chap06;

import common.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingTest {
    List<Dish> dishes = null;
    @BeforeEach
    void setup() {
        dishes = Arrays.asList(
                Dish.builder().name("pork").calories(800).type(Dish.TYPE.MEAT).build(),
                Dish.builder().name("beef").calories(700).type(Dish.TYPE.MEAT).build(),
                Dish.builder().name("salmon").calories(450).type(Dish.TYPE.FISH).build(),
                Dish.builder().name("rice").calories(300).type(Dish.TYPE.OTHER).build());
    }

    @Test
    void simpleMapping() {
        Map<Dish.TYPE, List<Dish>> collect = dishes.stream().collect(Collectors.groupingBy(Dish::getType));
        // TODO
    }
}
