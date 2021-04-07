package de.pflugradts.guicedemo.instance;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.instance.InstanceModule.NAME_PI;
import static de.pflugradts.guicedemo.instance.InstanceModule.PI;
import static org.assertj.core.api.Assertions.assertThat;

class InstanceInterfaceTest {

    private static class Testbed {

        @Inject @Named(NAME_PI)
        private float pi;

        public float getPi() {
            return pi;
        }

    }

    @Test
    void shouldBeCreated() {
        // given
        final var injector = Guice.createInjector(new InstanceModule());

        // when
        final var actual = injector.getInstance(Testbed.class);

        // then
        assertThat(actual).isNotNull()
            .extracting(Testbed::getPi).isNotNull()
            .isEqualTo(PI);
    }

}
