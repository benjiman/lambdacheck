package com.benjiweber.propertytests;

interface Property {
    String name();
    Runnable check();
    static Property create(String name, Runnable check) {
        return new Property() {
            public String name() { return name; }
            public Runnable check() { return check; }
        };
    }
}
