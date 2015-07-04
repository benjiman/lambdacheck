package com.benjiweber.propertytests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

interface StreamUtils {
    // This should be a spliterator
    static <T> Stream<Pair<T,T>> pairUp(Stream<T> input, int limit) {
        List<T> ts = input.limit(limit).collect(toList());
        List<Pair<T,T>> results = new ArrayList<>();
        for (int i = 0; i < ts.size(); i+=2) {
            results.add(Pair.pair(ts.get(i), ts.get(i+1)));
        }
        return results.stream();
    }

}
