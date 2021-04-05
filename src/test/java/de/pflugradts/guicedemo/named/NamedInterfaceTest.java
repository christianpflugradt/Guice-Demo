package de.pflugradts.guicedemo.named;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.named.NamedModule.NAME_A;
import static de.pflugradts.guicedemo.named.NamedModule.NAME_B;
import static org.assertj.core.api.Assertions.assertThat;

class NamedInterfaceTest {

    private static class Wrapper {

        @Inject @Named(NAME_A)
        private NamedInterface namedInterfaceA;
        @Inject @Named(NAME_B)
        private NamedInterface namedInterfaceB;

        public NamedInterface getInterfaceNamedA() {
            return namedInterfaceA;
        }

        public NamedInterface getInterfaceNamedB() {
            return namedInterfaceB;
        }

    }

    private final Wrapper wrapper = Guice.createInjector(new NamedModule()).getInstance(Wrapper.class);

    @Test
    void shouldBeCreated() {
        assertThat(wrapper).isNotNull();
        final var namedA = wrapper.getInterfaceNamedA();
        final var namedB = wrapper.getInterfaceNamedB();

        assertThat(namedA).isNotNull().isInstanceOf(NamedClassA.class);
        assertThat(namedB).isNotNull().isInstanceOf(NamedClassB.class);
    }

}
