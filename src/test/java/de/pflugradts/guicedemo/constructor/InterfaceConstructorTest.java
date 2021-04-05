package de.pflugradts.guicedemo.constructor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import de.pflugradts.guicedemo.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceConstructorTest {

    static class Wrapper {
        @Inject
        InterfaceConstructor interfaceConstructor;
    }

    private Wrapper wrapper;

    @BeforeEach
    private void setup() {
        wrapper = Guice.createInjector(new Module()).getInstance(Wrapper.class);
    }

    @Test
    void shouldBeCreated() {
        assertThat(wrapper).isNotNull()
            .extracting(w -> w.interfaceConstructor).isNotNull()
            .isInstanceOf(ClassConstructor.class);

        final var actual = wrapper.interfaceConstructor;
        assertThat(actual.getS()).isNotNull().isEqualTo(Module.CONSTRUCTOR_STRING);
        assertThat(actual.getI()).isNotNull().isEqualTo(Module.CONSTRUCTOR_INT);
        assertThat(actual.isB()).isNotNull().isEqualTo(Module.CONSTRUCTOR_BOOL);
    }

}
