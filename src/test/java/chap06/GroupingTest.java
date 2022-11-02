package chap06;

import common.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

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
        Map<Dish.TYPE, List<Dish>> collect = dishes.stream().collect(groupingBy(Dish::getType));
        collect.entrySet().stream().forEach(entry -> {
            System.out.println("KEY : " + entry.getKey());
            String typeDishes = entry.getValue().stream().map(dish -> dish.getName()).collect(joining(", "));
            System.out.println("Dished : " + typeDishes);
        });
    }

    @Test
    @DisplayName("그룹핑과 함께 요소를 조작하기 - mapping")
    void groupingAndControl() {
        Map<Dish.TYPE, List<String>> collect = dishes.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        collect.entrySet().stream().forEach(entry -> {
            System.out.println("KEY : " + entry.getKey());
            String typeDishes = entry.getValue().stream().collect(joining(", "));
            System.out.println("Dished : " + typeDishes);
        });
    }

    @Test
    @DisplayName("다수준 그룹화")
    void multiGrouping() {
        Map<Dish.TYPE, Map<Dish.CaloricLevel, List<Dish>>> collect = dishes.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 500)
                                return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700)
                                return Dish.CaloricLevel.NORMAL;
                            else
                                return Dish.CaloricLevel.FAT;
                        })
                )
        );

        collect.entrySet().stream().forEach(byType -> {
            System.out.println("# TYPE : " + byType.getKey());
            byType.getValue().entrySet().stream().forEach(byCaloric -> {
                System.out.println("## Calories : " + byCaloric.getKey());
                String dishNames = byCaloric.getValue().stream().map(dish -> dish.getName()).collect(joining(", "));
                System.out.println("### Dish Names : " + dishNames);
            });
            System.out.println();
        });
    }
}
