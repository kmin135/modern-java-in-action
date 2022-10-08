package chap02.service;

import chap02.dto.Apple;

public interface ApplePredicate {
    boolean test(Apple apple);
}
