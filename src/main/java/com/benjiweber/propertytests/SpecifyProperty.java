package com.benjiweber.propertytests;

import java.util.function.BiPredicate;

public interface SpecifyProperty<T> {
    void forAll(Generator<T> generator, BiPredicate<T, T> condition);
}
