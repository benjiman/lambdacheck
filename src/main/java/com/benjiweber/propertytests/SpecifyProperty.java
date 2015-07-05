package com.benjiweber.propertytests;

import java.util.function.BiPredicate;

public interface SpecifyProperty<T> {
    void forAll(Generator<T> generator, TriPredicate<T, T, T> condition);
    void forAll(Generator<T> generator, BiPredicate<T, T> condition);
    void forAll(BiPredicate<T, T> condition);
}
