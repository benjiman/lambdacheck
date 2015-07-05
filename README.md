# lambdacheck
Quick attempt at cloning of scalacheck for Property Based Testing. (Just a spike, not ready for use)

Specify properties with lambdas, executed by JUnit so it can run in existing suites.

```java
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

```

![Screenshot](http://files.benjiweber.co.uk/b/lambdacheck.png)


```java

You can also mix-in generators you have already writen without having to specify them in the test

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
```