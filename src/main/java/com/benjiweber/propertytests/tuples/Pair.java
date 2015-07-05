package com.benjiweber.propertytests.tuples;

public interface Pair<T,U> {
    T one();
    U two();
    static <T,U> Pair<T,U> pair(T t, U u) {
        return new Pair<T,U>(){
            public T one() { return t; }
            public U two() { return u; }
        };
    }
}
