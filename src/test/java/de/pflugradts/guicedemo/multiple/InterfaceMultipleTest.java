package de.pflugradts.guicedemo.multiple;

import com.google.inject.Guice;
import com.google.inject.Inject;
import de.pflugradts.guicedemo.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceMultipleTest {

    static class Wrapper {

        @Inject
        private Set<InterfaceMultiple> interfaceMultiples;

        public List<InterfaceMultiple> getInterfaceMultiples() {
            return List.copyOf(interfaceMultiples);
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
            .extracting(Wrapper::getInterfaceMultiples).isNotNull()
            .asList().isNotEmpty().hasSize(3);
        assertThat(wrapper.getInterfaceMultiples()).hasAtLeastOneElementOfType(ClassMultiple1.class);
        assertThat(wrapper.getInterfaceMultiples()).hasAtLeastOneElementOfType(ClassMultiple2.class);
        assertThat(wrapper.getInterfaceMultiples()).hasAtLeastOneElementOfType(ClassMultiple3.class);
    }

}
