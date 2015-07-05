package com.benjiweber.lambdacheck.example;

import com.benjiweber.propertytests.Generator;
import com.benjiweber.propertytests.ImplicitGenerator;
import com.benjiweber.propertytests.Properties;
import com.benjiweber.propertytests.PropertyBasedTest;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(PropertyBasedTest.class)
public class SpecificationWithImplicitGenerator extends Properties<Integer> implements IntegerGenerator {{
    property("multiplication is commutative").forAll((a,b) ->
        a*b == b*a
    );

    property("will fail").forAll((a,b) ->
        a*b == b*a*a
    );
}}

interface IntegerGenerator extends ImplicitGenerator<Integer> {
    default Stream<Integer> inputs() {
        return IntStream.generate(new Random()::nextInt)
            .boxed();
    }
}