package de.pflugradts.guicedemo.constructor;

import com.google.inject.AbstractModule;

public class ConstructorModule extends AbstractModule {

    public static final String CONSTRUCTOR_STRING = "hello, world!";
    public static final int CONSTRUCTOR_INT = 314;
    public static final boolean CONSTRUCTOR_BOOL = true;

    @Override
    public void configure() {
        bind(String.class).toInstance(CONSTRUCTOR_STRING);
        bind(Integer.class).toInstance(CONSTRUCTOR_INT);
        bind(Boolean.class).toInstance(CONSTRUCTOR_BOOL);
        try {
            bind(ConstructorInterface.class).toConstructor(
                ConstructorClass.class.getConstructor(String.class, Integer.TYPE, Boolean.TYPE));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
