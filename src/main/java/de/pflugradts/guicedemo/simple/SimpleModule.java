package de.pflugradts.guicedemo.simple;

import com.google.inject.AbstractModule;

public class SimpleModule extends AbstractModule {

    @Override
    public void configure() {
        bind(SimpleInterface.class).to(SimpleClass.class);
    }

}
