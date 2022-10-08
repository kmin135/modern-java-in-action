package chap02.service;

import chap02.dto.Apple;
import chap02.dto.Apple.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleService<T> {
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filterApplesByPredicate(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T e : inventory) {
            if(predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
