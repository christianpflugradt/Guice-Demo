package de.pflugradts.guicedemo.constructor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.constructor.ConstructorModule.CONSTRUCTOR_BOOL;
import static de.pflugradts.guicedemo.constructor.ConstructorModule.CONSTRUCTOR_INT;
import static de.pflugradts.guicedemo.constructor.ConstructorModule.CONSTRUCTOR_STRING;
import static org.assertj.core.api.Assertions.assertThat;

class ConstructorInterfaceTest {

    private static class Wrapper {
        @Inject
        ConstructorInterface constructorInterface;
    }

    private final Wrapper wrapper = Guice.createInjector(new ConstructorModule()).getInstance(Wrapper.class);

    @Test
    void shouldBeCreated() {
        assertThat(wrapper).isNotNull()
            .extracting(w -> w.constructorInterface).isNotNull()
            .isInstanceOf(ConstructorClass.class);

        final var actual = wrapper.constructorInterface;
        assertThat(actual.getS()).isNotNull().isEqualTo(CONSTRUCTOR_STRING);
        assertThat(actual.getI()).isNotNull().isEqualTo(CONSTRUCTOR_INT);
        assertThat(actual.isB()).isNotNull().isEqualTo(CONSTRUCTOR_BOOL);
    }

}
