package de.pflugradts.guicedemo.instance;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class InstanceModule extends AbstractModule {

    public static final float PI = 3.141592f;
    public static final String NAME_PI = "Pi";

    @Override
    public void configure() {
        bind(Float.TYPE).annotatedWith(Names.named(NAME_PI)).toInstance(PI);
    }

}
