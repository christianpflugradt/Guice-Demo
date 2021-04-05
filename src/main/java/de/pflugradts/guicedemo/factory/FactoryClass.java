package de.pflugradts.guicedemo.factory;

public class FactoryClass implements FactoryInterface {

    private final String name;

    private FactoryClass(String name) {
        this.name = name;
    }

    public static FactoryClass createInstance(String name) {
        return new FactoryClass(name);
    }

    @Override
    public String getName() {
        return name;
    }

}
