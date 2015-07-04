package com.benjiweber.propertytests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Properties<T> {

    List<Property> properties = new ArrayList<>();

    protected SpecifyProperty<T> property(String name) {
        return (generator, condition) -> {
            properties.add(
                    Property.create(name, () -> {
                        StreamUtils.pairUp(generator.inputs(), 100).forEach(
                            pair -> assertTrue(message(name,pair), condition.test(pair.left(), pair.right()))
                        );
                    })
            );
        };
    }

    private String message(String name, Pair<T,T> pair) {
        return "Property " + name + " violated by (" +  pair.left() + ", " + pair.right() + ")";
    }

}
