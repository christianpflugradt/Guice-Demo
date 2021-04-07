package de.pflugradts.guicedemo.factory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.factory.FactoryModule.FACTORY_PROVIDER;
import static de.pflugradts.guicedemo.factory.FactoryModule.FACTORY_PROVIDES;
import static de.pflugradts.guicedemo.factory.FactoryModule.NAME_PROVIDER;
import static org.assertj.core.api.Assertions.assertThat;

class FactoryInterfaceTest {

    private static class Testbed {

        @Inject
        private FactoryInterface interfaceProvidesFactory;
        @Inject @Named(NAME_PROVIDER)
        private FactoryInterface interfaceProviderFactory;

        public FactoryInterface getInterfaceProvidesFactory() {
            return interfaceProvidesFactory;
        }

        public FactoryInterface getInterfaceProviderFactory() {
            return interfaceProviderFactory;
        }

    }

    @Test
    void shouldBeCreated() {
        // given
        final var injector = Guice.createInjector(new FactoryModule());

        // when
        final var actual = injector.getInstance(Testbed.class);

        // then
        assertThat(actual).isNotNull();
        final var provides = actual.getInterfaceProvidesFactory();
        final var provider = actual.getInterfaceProviderFactory();

        assertThat(provides).isNotNull().isInstanceOf(FactoryClass.class)
            .extracting(FactoryInterface::getName).isNotNull().isEqualTo(FACTORY_PROVIDES);
        assertThat(provider).isNotNull().isInstanceOf(FactoryClass.class)
            .extracting(FactoryInterface::getName).isNotNull().isEqualTo(FACTORY_PROVIDER);
    }

}
