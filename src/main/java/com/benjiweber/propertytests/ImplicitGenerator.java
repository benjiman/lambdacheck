package com.benjiweber.propertytests;

import java.util.stream.Stream;

public interface ImplicitGenerator<T> extends Generator<T> {
    default Stream<T> inputs() {
        return Stream.empty();
    }
}