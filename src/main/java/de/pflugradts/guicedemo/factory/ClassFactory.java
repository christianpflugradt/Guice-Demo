package de.pflugradts.guicedemo.factory;

public class ClassFactory implements InterfaceFactory {

    private final String name;

    private ClassFactory(String name) {
        this.name = name;
    }

    public static ClassFactory createInstance(String name) {
        return new ClassFactory(name);
    }

    @Override
    public String getName() {
        return name;
    }

}
