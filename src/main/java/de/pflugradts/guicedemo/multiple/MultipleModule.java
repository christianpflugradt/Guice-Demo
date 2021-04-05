package de.pflugradts.guicedemo.multiple;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import java.util.List;

public class MultipleModule extends AbstractModule {

    @Override
    public void configure() {
        final var multiBinder = Multibinder.newSetBinder(binder(), MultipleInterface.class);
        List.of(MultipleClass1.class, MultipleClass2.class, MultipleClass3.class)
            .forEach(clazz -> multiBinder.addBinding().to(clazz));
    }

}
