package de.pflugradts.guicedemo.named;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class NamedModule extends AbstractModule {

    public static final String NAME_A = "A";
    public static final String NAME_B = "B";

    @Override
    public void configure() {
        bind(NamedInterface.class).annotatedWith(Names.named(NAME_A)).to(NamedClassA.class);
        bind(NamedInterface.class).annotatedWith(Names.named(NAME_B)).to(NamedClassB.class);
    }

}
