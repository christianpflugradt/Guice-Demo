package de.pflugradts.guicedemo.factory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.pflugradts.guicedemo.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceFactoryTest {

    static class Wrapper {

        @Inject
        private InterfaceFactory interfaceProvidesFactory;
        @Inject @Named("Custom")
        private InterfaceFactory interfaceProviderFactory;

        public InterfaceFactory getInterfaceProvidesFactory() {
            return interfaceProvidesFactory;
        }

        public InterfaceFactory getInterfaceProviderFactory() {
            return interfaceProviderFactory;
        }

    }

    private Wrapper wrapper;

    @BeforeEach
    private void setup() {
        wrapper = Guice.createInjector(new Module()).getInstance(Wrapper.class);
    }

    @Test
    void shouldBeCreated() {
        assertThat(wrapper).isNotNull();
        final var provides = wrapper.getInterfaceProvidesFactory();
        final var provider = wrapper.getInterfaceProviderFactory();

        assertThat(provides).isNotNull().isInstanceOf(ClassFactory.class)
            .extracting(InterfaceFactory::getName).isNotNull().isEqualTo(Module.FACTORY_PROVIDES);
        assertThat(provider).isNotNull().isInstanceOf(ClassFactory.class)
            .extracting(InterfaceFactory::getName).isNotNull().isEqualTo(Module.FACTORY_PROVIDER);
    }

}
