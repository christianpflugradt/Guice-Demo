package de.pflugradts.guicedemo.multiple;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MultipleInterfaceTest {

    private static class Wrapper {

        @Inject
        private Set<MultipleInterface> multipleInterfaces;

        public List<MultipleInterface> getInterfaceMultiples() {
            return List.copyOf(multipleInterfaces);
        }

    }

    private final Wrapper wrapper = Guice.createInjector(new MultipleModule()).getInstance(Wrapper.class);

    @Test
    void shouldBeCreated() {

        assertThat(wrapper).isNotNull()
            .extracting(Wrapper::getInterfaceMultiples).isNotNull()
            .asList().isNotEmpty().hasSize(3);
        assertThat(wrapper.getInterfaceMultiples()).hasAtLeastOneElementOfType(MultipleClass1.class);
        assertThat(wrapper.getInterfaceMultiples()).hasAtLeastOneElementOfType(MultipleClass2.class);
        assertThat(wrapper.getInterfaceMultiples()).hasAtLeastOneElementOfType(MultipleClass3.class);
    }

}
