package de.pflugradts.guicedemo.named;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.named.NamedModule.NAME_A;
import static de.pflugradts.guicedemo.named.NamedModule.NAME_B;
import static org.assertj.core.api.Assertions.assertThat;

class NamedInterfaceTest {

    private static class Testbed {

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

    @Test
    void shouldBeCreated() {
        // given
        final var injector = Guice.createInjector(new NamedModule());

        // when
        final var actual = injector.getInstance(Testbed.class);

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getInterfaceNamedA()).isNotNull().isInstanceOf(NamedClassA.class);
        assertThat(actual.getInterfaceNamedB()).isNotNull().isInstanceOf(NamedClassB.class);
    }

}
