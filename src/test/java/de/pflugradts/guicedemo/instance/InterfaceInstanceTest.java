package de.pflugradts.guicedemo.instance;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.pflugradts.guicedemo.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceInstanceTest {

    static class Wrapper {

        @Inject @Named("Pi")
        private float pi;

        public float getPi() {
            return pi;
        }
    }

    private Wrapper wrapper;

    @BeforeEach
    private void setup() {
        wrapper = Guice.createInjector(new Module()).getInstance(Wrapper.class);
    }

    @Test
    void shouldBeCreated() {
        assertThat(wrapper).isNotNull()
            .extracting(Wrapper::getPi).isNotNull()
            .isEqualTo(Module.PI);
    }

}
