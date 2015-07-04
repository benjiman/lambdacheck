package com.benjiweber.propertytests;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import static org.junit.runner.Description.createTestDescription;

public class PropertyBasedTest extends Runner {
    private final Properties<?> test;
    private Class<? extends Properties<?>> klass;

    public PropertyBasedTest(Class<? extends Properties<?>> klass) throws InitializationError {
        this.klass = klass;
        try {
            this.test = klass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int testCount() {
        return test.properties.size();
    }

    @Override
    public Description getDescription() {
        Description description = createTestDescription(klass, klass.getSimpleName());
        test.properties.stream().forEach(
            property -> description.addChild(createTestDescription(klass, property.name()))
        );
        return description;
    }

    @Override
    public void run(RunNotifier notifier) {
        test.properties.forEach(property -> {
            Description desc = createTestDescription(klass, property.name());
            try {
                notifier.fireTestStarted(desc);
                property.check().run();
                notifier.fireTestFinished(desc);
            } catch (Exception e) {
                notifier.fireTestFailure(new Failure(desc, e));
            }
        });
    }
}
