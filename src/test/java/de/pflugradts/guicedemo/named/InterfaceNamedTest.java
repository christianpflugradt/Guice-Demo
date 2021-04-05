package de.pflugradts.guicedemo.named;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.pflugradts.guicedemo.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceNamedTest {

    static class Wrapper {

        @Inject @Named("A")
        private InterfaceNamed interfaceNamedA;
        @Inject @Named("B")
        private InterfaceNamed interfaceNamedB;

        public InterfaceNamed getInterfaceNamedA() {
            return interfaceNamedA;
        }

        public InterfaceNamed getInterfaceNamedB() {
            return interfaceNamedB;
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
        final var namedA = wrapper.getInterfaceNamedA();
        final var namedB = wrapper.getInterfaceNamedB();

        assertThat(namedA).isNotNull().isInstanceOf(ClassNamedA.class);
        assertThat(namedB).isNotNull().isInstanceOf(ClassNamedB.class);
    }

}
