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

    private static class InjectionWrapper {

        @Inject
        private SimpleInterface simpleInterfaceFieldInjected;
        private SimpleInterface simpleInterfaceMethodInjected;
        private final SimpleInterface simpleInterfaceConstructorInjected;

        @Inject
        public InjectionWrapper(SimpleInterface constructorParam) {
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

        private final InjectionWrapper wrapper = Guice.createInjector(new SimpleModule()).getInstance(InjectionWrapper.class);

        @Test
        void shouldBeCreated() {
            assertThat(wrapper).isNotNull();
            final var fieldInj = wrapper.getSimpleInterfaceFieldInjected();
            final var methodInj = wrapper.getSimpleInterfaceMethodInjected();
            final var constrInj = wrapper.getSimpleInterfaceConstructorInjected();

            assertThat(fieldInj).isNotNull().isInstanceOf(SimpleClass.class)
                .isNotSameAs(methodInj)
                .isNotSameAs(constrInj);
            assertThat(methodInj).isNotNull().isInstanceOf(SimpleClass.class)
                .isNotSameAs(constrInj);
            assertThat(constrInj).isNotNull().isInstanceOf(SimpleClass.class);
        }

    }

    private static class SingletonWrapper {

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

        private final SingletonWrapper wrapper = Guice.createInjector(new SingletonModule()).getInstance(SingletonWrapper.class);

        @Test
        void shouldBeCreated() {
            assertThat(wrapper).isNotNull();
            final var singletonA = wrapper.getSimpleInterfaceSingletonA();
            final var singletonB = wrapper.getSimpleInterfaceSingletonB();

            assertThat(singletonA).isNotNull().isInstanceOf(SimpleClass.class);
            assertThat(singletonB).isNotNull().isInstanceOf(SimpleClass.class);
            assertThat(singletonA).isSameAs(singletonB);
        }

    }

}
