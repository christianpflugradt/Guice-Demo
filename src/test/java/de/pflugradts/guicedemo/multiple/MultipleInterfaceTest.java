package de.pflugradts.guicedemo.multiple;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MultipleInterfaceTest {

    private static class Testbed {

        @Inject
        private Set<MultipleInterface> multipleInterfaces;

        public List<MultipleInterface> getInterfaceMultiples() {
            return List.copyOf(multipleInterfaces);
        }

    }

    @Test
    void shouldBeCreated() {
        // given
        final var injector = Guice.createInjector(new MultipleModule());

        // when
        final var actual = injector.getInstance(Testbed.class);

        // then
        assertThat(actual).isNotNull()
            .extracting(Testbed::getInterfaceMultiples).isNotNull()
            .asList().isNotEmpty()
            .hasSize(3)
            .hasAtLeastOneElementOfType(MultipleClass1.class)
            .hasAtLeastOneElementOfType(MultipleClass2.class)
            .hasAtLeastOneElementOfType(MultipleClass3.class);
    }

}
