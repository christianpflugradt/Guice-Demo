package de.pflugradts.guicedemo.simple;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.pflugradts.guicedemo.simple.singleton.SingletonModule;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static de.pflugradts.guicedemo.simple.singleton.SingletonModule.NAME_SINGLETON;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleInterfaceTest {

    private static class InjectionTestbed {

        @Inject
        private SimpleInterface simpleInterfaceFieldInjected;
        private SimpleInterface simpleInterfaceMethodInjected;
        private final SimpleInterface simpleInterfaceConstructorInjected;

        @Inject
        public InjectionTestbed(SimpleInterface constructorParam) {
            this.simpleInterfaceConstructorInjected = constructorParam;
        }

        @Inject
        private void init(SimpleInterface methodParam) {
            this.simpleInterfaceMethodInjected = methodParam;
        }

        public SimpleInterface getSimpleInterfaceFieldInjected() {
            return simpleInterfaceFieldInjected;
        }

        public SimpleInterface getSimpleInterfaceMethodInjected() {
            return simpleInterfaceMethodInjected;
        }

        public SimpleInterface getSimpleInterfaceConstructorInjected() {
            return simpleInterfaceConstructorInjected;
        }

    }

    @Nested
    class InjectionTest {

        @Test
        void shouldBeCreated() {
            // given
            final var injector = Guice.createInjector(new SimpleModule());

            // when
            final var actual = injector.getInstance(InjectionTestbed.class);

            // then
            assertThat(actual).isNotNull();
            assertThat(actual.getSimpleInterfaceFieldInjected()).isNotNull()
                .isInstanceOf(SimpleClass.class)
                .isNotSameAs(actual.getSimpleInterfaceMethodInjected())
                .isNotSameAs(actual.getSimpleInterfaceConstructorInjected());
            assertThat(actual.getSimpleInterfaceMethodInjected()).isNotNull()
                .isInstanceOf(SimpleClass.class)
                .isNotSameAs(actual.getSimpleInterfaceConstructorInjected());
            assertThat(actual.getSimpleInterfaceConstructorInjected()).isNotNull()
                .isInstanceOf(SimpleClass.class);
        }

    }

    private static class SingletonTestbed {

        @Inject @Named(NAME_SINGLETON)
        private SimpleInterface simpleInterfaceSingletonA;
        @Inject @Named(NAME_SINGLETON)
        private SimpleInterface simpleInterfaceSingletonB;

        public SimpleInterface getSimpleInterfaceSingletonA() {
            return simpleInterfaceSingletonA;
        }

        public SimpleInterface getSimpleInterfaceSingletonB() {
            return simpleInterfaceSingletonB;
        }

    }

    @Nested
    class SingletonTest {

        @Test
        void shouldBeCreated() {
            // given
            final var injector = Guice.createInjector(new SingletonModule());

            // when
            final var actual = injector.getInstance(SingletonTestbed.class);

            // then
            assertThat(actual).isNotNull();
            assertThat(actual.getSimpleInterfaceSingletonA()).isNotNull()
                .isInstanceOf(SimpleClass.class);
            assertThat(actual.getSimpleInterfaceSingletonB()).isNotNull()
                .isInstanceOf(SimpleClass.class);
            assertThat(actual.getSimpleInterfaceSingletonA())
                .isSameAs(actual.getSimpleInterfaceSingletonB());
        }

    }

}
