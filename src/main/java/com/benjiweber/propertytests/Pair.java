package com.benjiweber.propertytests;

interface Pair<T,U> {
    T left();
    U right();
    static <T,U> Pair<T,U> pair(T t, U u) {
        return new Pair<T,U>(){
            public T left() { return t; }
            public U right() { return u; }
        };
    }
}
