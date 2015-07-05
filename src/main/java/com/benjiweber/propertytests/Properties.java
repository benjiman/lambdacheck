package com.benjiweber.propertytests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class Properties<T> implements Generator<T>, ImplicitGenerator<T> {

    List<Property> properties = new ArrayList<>();

    protected SpecifyProperty<T> property(String name) {
        return new SpecifyProperty<T>() {
            public void forAll(Generator<T> generator, BiPredicate<T, T> condition) {
                properties.add(
                    Property.create(name, () -> {
                        StreamUtils.pairUp(generator.inputs(), 100)
                            .peek(pair -> System.out.println("Checking " + pair.left() + ", " + pair.right()))
                            .forEach(
                                    pair -> assertTrue(message(name, pair), condition.test(pair.left(), pair.right()))
                            );
                    })
                );
            }

            public void forAll(BiPredicate<T, T> condition) {
                forAll(Properties.this, condition);
            }
        };
    }

    private String message(String name, Pair<T,T> pair) {
        return "\n\tProperty " + name + " violated by (\n\t\targ0 = " +  pair.left() + ",\n\t\targ1 = " + pair.right() + "\n\t)\n";
    }

}


