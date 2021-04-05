package de.pflugradts.guicedemo.instance;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.instance.InstanceModule.NAME_PI;
import static de.pflugradts.guicedemo.instance.InstanceModule.PI;
import static org.assertj.core.api.Assertions.assertThat;

class InstanceInterfaceTest {

    private static class Wrapper {

        @Inject @Named(NAME_PI)
        private float pi;

        public float getPi() {
            return pi;
        }

    }

    private final Wrapper wrapper = Guice.createInjector(new InstanceModule()).getInstance(Wrapper.class);

    @Test
    void shouldBeCreated() {
        assertThat(wrapper).isNotNull()
            .extracting(Wrapper::getPi).isNotNull()
            .isEqualTo(PI);
    }

}
