package de.pflugradts.guicedemo;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    public void configure() {
        simple();
        singleton();
    }

    private void simple() {
    }



    private void singleton() {
    }

}
