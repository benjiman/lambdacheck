package com.benjiweber.propertytests.tuples;

public interface Triple<T,U,V> {
    T one();
    U two();
    V three();
    static <T,U,V> Triple<T,U,V> triple(T t, U u, V v) {
        return new Triple<T,U,V>(){
            public T one() { return t; }
            public U two() { return u; }
            public V three() { return v; }
        };
    }
}
