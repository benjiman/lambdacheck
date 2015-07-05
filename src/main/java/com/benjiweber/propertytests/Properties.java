package com.benjiweber.propertytests;

import com.benjiweber.propertytests.tuples.Pair;
import com.benjiweber.propertytests.tuples.Triple;
import junit.framework.AssertionFailedError;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Properties<T> implements Generator<T>, ImplicitGenerator<T> {

    List<Property> properties = new ArrayList<>();

    protected SpecifyProperty<T> property(String name) {
        return new SpecifyProperty<T>() {
            @Override
            public void forAll(Generator<T> generator, TriPredicate<T, T, T> condition) {

                properties.add(
                    Property.create(name, () -> {
                        StreamUtils.tripleUp(generator.inputs(), 100)
                            .peek(tuple -> System.out.println("Checking " + tuple.one() + ", " + tuple.two() + ", " + tuple.three()))
                            .forEach(
                                reportTripleErrors(name, tuple -> assertTrue(message(name, tuple), condition.test(tuple.one(), tuple.two(), tuple.three())))
                            );
                    })
                );
            }

            public void forAll(Generator<T> generator, BiPredicate<T, T> condition) {
                properties.add(
                    Property.create(name, () -> {
                        StreamUtils.pairUp(generator.inputs(), 100)
                            .peek(tuple -> System.out.println("Checking " + tuple.one() + ", " + tuple.two()))
                            .forEach(
                                reportPairErrors(name, pair -> assertTrue(message(name, pair), condition.test(pair.one(), pair.two())))
                            );
                    })
                );
            }

            public void forAll(BiPredicate<T, T> condition) {
                forAll(Properties.this, condition);
            }
        };
    }

    private Consumer<Pair<T,T>> reportPairErrors(String name, Consumer<Pair<T,T>> consumer) {
        return input -> {
            try {
                consumer.accept(input);
            } catch (AssertionError e) {
                throw e;
            } catch (Exception e) {
                fail(error(message(name, input), e));
            }
        };
    }

    private Consumer<Triple<T,T,T>> reportTripleErrors(String name, Consumer<Triple<T,T,T>> consumer) {
        return input -> {
            try {
                consumer.accept(input);
            } catch (AssertionError e) {
                throw e;
            } catch (Exception e) {
                fail(error(message(name, input), e));
            }
        };
    }

    private String error(String message, Exception e) {
        return message + "\tThrew Exception: " +  e.getMessage() + "\n";
    }

    private String message(String name, Pair<T,T> pair) {
        return "\n\tProperty " + name + " violated by (\n\t\targ0 = " +  pair.one() + ",\n\t\targ1 = " + pair.two() + "\n\t)\n";
    }

    private String message(String name, Triple<T,T,T> pair) {
        return "\n\tProperty " + name + " violated by (\n\t\targ0 = " +  pair.one() + ",\n\t\targ1 = " + pair.two() + ",\n\t\targ2 = " + pair.three() + "\n\t)\n";
    }

}



