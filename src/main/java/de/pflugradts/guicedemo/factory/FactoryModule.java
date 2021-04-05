package de.pflugradts.guicedemo.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class FactoryModule extends AbstractModule {

    public static final String FACTORY_PROVIDES = "created by provides";
    public static final String FACTORY_PROVIDER = "created by provider";
    public static final String NAME_PROVIDER = "Provider";

    @Override
    public void configure() {
        bind(FactoryInterface.class).annotatedWith(Names.named(NAME_PROVIDER)).toProvider(InterfaceFactoryProvider.class);
    }

    static class InterfaceFactoryProvider implements Provider<FactoryInterface> {

        @Override
        public FactoryInterface get() {
            return FactoryClass.createInstance(FACTORY_PROVIDER);
        }

    }

    @Provides
    public static FactoryInterface createClassFactoryDefaultInstance() {
        return FactoryClass.createInstance(FACTORY_PROVIDES);
    }

}
