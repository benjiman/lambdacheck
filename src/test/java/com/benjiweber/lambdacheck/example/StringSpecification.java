package com.benjiweber.lambdacheck.example;

import com.benjiweber.propertytests.Generator;
import com.benjiweber.propertytests.Properties;
import com.benjiweber.propertytests.PropertyBasedTest;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.stream.IntStream;

@RunWith(PropertyBasedTest.class)
public class StringSpecification extends Properties<String> {{
    Generator<String> generator = () ->
        IntStream.generate(new Random()::nextInt)
            .boxed()
            .map(Object::toString);

    property("startsWith").forAll(generator, (a,b) ->
            (a+b).startsWith(a)
    );

    property("thisWillFail").forAll(generator, (a,b) ->
        (a+b).startsWith("hello")
    );

}}
