package de.pflugradts.guicedemo.constructor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.constructor.ConstructorModule.CONSTRUCTOR_BOOL;
import static de.pflugradts.guicedemo.constructor.ConstructorModule.CONSTRUCTOR_INT;
import static de.pflugradts.guicedemo.constructor.ConstructorModule.CONSTRUCTOR_STRING;
import static org.assertj.core.api.Assertions.assertThat;

class ConstructorInterfaceTest {

    private static class Testbed {
        @Inject
        ConstructorInterface constructorInterface;
    }

    @Test
    void shouldBeCreated() {
        // given
        final var injector = Guice.createInjector(new ConstructorModule());

        // when
        final var actual = injector.getInstance(Testbed.class);

        // then
        assertThat(actual).isNotNull()
            .extracting(w -> w.constructorInterface).isNotNull()
            .isInstanceOf(ConstructorClass.class);

        assertThat(actual.constructorInterface.getS()).isNotNull().isEqualTo(CONSTRUCTOR_STRING);
        assertThat(actual.constructorInterface.getI()).isNotNull().isEqualTo(CONSTRUCTOR_INT);
        assertThat(actual.constructorInterface.isB()).isNotNull().isEqualTo(CONSTRUCTOR_BOOL);
    }

}
