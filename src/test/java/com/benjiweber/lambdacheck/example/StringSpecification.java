package com.benjiweber.lambdacheck.example;

import com.benjiweber.propertytests.Generator;
import com.benjiweber.propertytests.Properties;
import com.benjiweber.propertytests.PropertyBasedTest;
import org.junit.runner.RunWith;

import java.util.Objects;
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

    property("this will fail").forAll(generator, (a,b) ->
        (a+b).startsWith("hello")
    );

    property("substring").forAll(generator, (a,b,c) ->
        Objects.equals(
            (a + b + c).substring(a.length(), a.length() + b.length()),
            b
        )
    );

    property("this will error").forAll(generator, (a,b,c) ->
        Objects.equals(
            (a + b + c).substring(a.length(), 1000 + a.length() + b.length()),
            b
        )
    );

}}
