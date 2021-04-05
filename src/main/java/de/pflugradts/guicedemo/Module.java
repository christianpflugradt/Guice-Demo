package de.pflugradts.guicedemo;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import de.pflugradts.guicedemo.constructor.ClassConstructor;
import de.pflugradts.guicedemo.constructor.InterfaceConstructor;
import de.pflugradts.guicedemo.factory.ClassFactory;
import de.pflugradts.guicedemo.factory.InterfaceFactory;
import de.pflugradts.guicedemo.multiple.ClassMultiple1;
import de.pflugradts.guicedemo.multiple.ClassMultiple2;
import de.pflugradts.guicedemo.multiple.ClassMultiple3;
import de.pflugradts.guicedemo.multiple.InterfaceMultiple;
import de.pflugradts.guicedemo.named.ClassNamedA;
import de.pflugradts.guicedemo.named.ClassNamedB;
import de.pflugradts.guicedemo.named.InterfaceNamed;
import de.pflugradts.guicedemo.simple.ClassSimple;
import de.pflugradts.guicedemo.simple.InterfaceSimple;

import java.util.List;

public class Module extends AbstractModule {

    public static final String CONSTRUCTOR_STRING = "hello, world!";
    public static final int CONSTRUCTOR_INT = 314;
    public static final boolean CONSTRUCTOR_BOOL = true;
    public static final String FACTORY_PROVIDES = "created by provides";
    public static final String FACTORY_PROVIDER = "created by provider";
    public static final float PI = 3.141592f;

    @Override
    public void configure() {
        simple();
        singleton();
        multiple();
        named();
        constructor();
        factory();
        instance();
    }

    private void simple() {
        bind(InterfaceSimple.class).to(ClassSimple.class);
    }

    private void multiple() {
        final var multiBinder = Multibinder.newSetBinder(binder(), InterfaceMultiple.class);
        List.of(ClassMultiple1.class, ClassMultiple2.class, ClassMultiple3.class)
            .forEach(clazz -> multiBinder.addBinding().to(clazz));
    }

    private void named() {
        bind(InterfaceNamed.class).annotatedWith(Names.named("A")).to(ClassNamedA.class);
        bind(InterfaceNamed.class).annotatedWith(Names.named("B")).to(ClassNamedB.class);
    }

    private void constructor() {
        bind(String.class).toInstance(CONSTRUCTOR_STRING);
        bind(Integer.class).toInstance(CONSTRUCTOR_INT);
        bind(Boolean.class).toInstance(CONSTRUCTOR_BOOL);
        try {
            bind(InterfaceConstructor.class).toConstructor(
                ClassConstructor.class.getConstructor(String.class, Integer.TYPE, Boolean.TYPE));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void factory() {
        bind(InterfaceFactory.class).annotatedWith(Names.named("Custom")).toProvider(InterfaceFactoryProvider.class);
    }

    private void singleton() {
        bind(InterfaceSimple.class).annotatedWith(Names.named("Singleton")).to(ClassSimple.class).in(Scopes.SINGLETON);
    }

    private void instance() {
        bind(Float.TYPE).annotatedWith(Names.named("Pi")).toInstance(PI);
    }

    static class InterfaceFactoryProvider implements Provider<InterfaceFactory> {

        @Override
        public InterfaceFactory get() {
            return ClassFactory.createInstance(FACTORY_PROVIDER);
        }

    }

    @Provides
    public static InterfaceFactory createClassFactoryDefaultInstance() {
        return ClassFactory.createInstance(FACTORY_PROVIDES);
    }

}
