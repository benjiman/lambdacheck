package com.benjiweber.propertytests;

import java.util.stream.Stream;

public interface Generator<T> {
    Stream<T> inputs();
}
