package de.pflugradts.guicedemo.simple.singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import de.pflugradts.guicedemo.simple.SimpleClass;
import de.pflugradts.guicedemo.simple.SimpleInterface;

public class SingletonModule extends AbstractModule {

    public static final String NAME_SINGLETON = "Singleton";

    @Override
    public void configure() {
        bind(SimpleInterface.class).annotatedWith(Names.named(NAME_SINGLETON)).to(SimpleClass.class).in(Scopes.SINGLETON);
    }

}
