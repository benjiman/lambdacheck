package com.benjiweber.propertytests;

import com.benjiweber.propertytests.tuples.Pair;
import com.benjiweber.propertytests.tuples.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// Can probably do these with spliterators
interface StreamUtils {

    static <T> Stream<Pair<T,T>> pairUp(Stream<T> input, int limit) {
        List<T> ts = input.limit(limit).collect(toList());
        List<Pair<T,T>> results = new ArrayList<>();
        for (int i = 0; i < ts.size()-2; i+=2) {
            results.add(Pair.pair(ts.get(i), ts.get(i+1)));
        }
        return results.stream();
    }

    static <T> Stream<Triple<T,T,T>> tripleUp(Stream<T> input, int limit) {
        List<T> ts = input.limit(limit).collect(toList());
        List<Triple<T,T,T>> results = new ArrayList<>();
        for (int i = 0; i < ts.size()-3; i+=3) {
            results.add(Triple.triple(ts.get(i), ts.get(i+1), ts.get(i+2)));
        }
        return results.stream();
    }

}
