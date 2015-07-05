package com.benjiweber.propertytests;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.List;

import static org.junit.runner.Description.createTestDescription;

public class PropertyBasedTest extends ParentRunner<Property> {
    private final Properties<?> test;
    private Class<? extends Properties<?>> klass;

    public PropertyBasedTest(Class<? extends Properties<?>> klass) throws InitializationError {
        super(klass);
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
    protected List<Property> getChildren() {
        return test.properties;
    }

    @Override
    protected Description describeChild(Property child) {
        return createTestDescription(klass, child.name());
    }

    @Override
    protected void runChild(Property child, RunNotifier notifier) {
        Description description = describeChild(child);
        runLeaf(statementFrom(child), description, notifier);
    }

    private static Statement statementFrom(Property property) {
        return new Statement() {
            public void evaluate() throws Throwable {
                property.check().run();
            }
        };
    }
}
