package de.pflugradts.guicedemo.simple;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.pflugradts.guicedemo.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceSimpleTest {

    static class InjectionWrapper {

        @Inject
        private InterfaceSimple interfaceSimpleFieldInjected;
        private InterfaceSimple interfaceSimpleMethodInjected;
        private final InterfaceSimple interfaceSimpleConstructorInjected;

        @Inject
        public InjectionWrapper(InterfaceSimple constructorParam) {
            this.interfaceSimpleConstructorInjected = constructorParam;
        }

        @Inject
        private void init(InterfaceSimple methodParam) {
            this.interfaceSimpleMethodInjected = methodParam;
        }

        public InterfaceSimple getInterfaceSimpleFieldInjected() {
            return interfaceSimpleFieldInjected;
        }

        public InterfaceSimple getInterfaceSimpleMethodInjected() {
            return interfaceSimpleMethodInjected;
        }

        public InterfaceSimple getInterfaceSimpleConstructorInjected() {
            return interfaceSimpleConstructorInjected;
        }

    }

    @Nested
    class InjectionTest {

        private InjectionWrapper wrapper;

        @BeforeEach
        private void setup() {
            wrapper = Guice.createInjector(new Module()).getInstance(InjectionWrapper.class);
        }

        @Test
        void shouldBeCreated() {
            assertThat(wrapper).isNotNull();
            final var fieldInj = wrapper.getInterfaceSimpleFieldInjected();
            final var methodInj = wrapper.getInterfaceSimpleMethodInjected();
            final var constrInj = wrapper.getInterfaceSimpleConstructorInjected();

            assertThat(fieldInj).isNotNull().isInstanceOf(ClassSimple.class)
                .isNotSameAs(methodInj)
                .isNotSameAs(constrInj);
            assertThat(methodInj).isNotNull().isInstanceOf(ClassSimple.class)
                .isNotSameAs(constrInj);
            assertThat(constrInj).isNotNull().isInstanceOf(ClassSimple.class);
        }

    }

    static class SingletonWrapper {

        @Inject @Named("Singleton")
        private InterfaceSimple interfaceSimpleSingletonA;
        @Inject @Named("Singleton")
        private InterfaceSimple interfaceSimpleSingletonB;

        public InterfaceSimple getInterfaceSimpleSingletonA() {
            return interfaceSimpleSingletonA;
        }

        public InterfaceSimple getInterfaceSimpleSingletonB() {
            return interfaceSimpleSingletonB;
        }

    }

    @Nested
    class SingletonTest {

        private SingletonWrapper wrapper;

        @BeforeEach
        private void setup() {
            wrapper = Guice.createInjector(new Module()).getInstance(SingletonWrapper.class);
        }

        @Test
        void shouldBeCreated() {
            assertThat(wrapper).isNotNull();
            final var singletonA = wrapper.getInterfaceSimpleSingletonA();
            final var singletonB = wrapper.getInterfaceSimpleSingletonB();

            assertThat(singletonA).isNotNull().isInstanceOf(ClassSimple.class);
            assertThat(singletonB).isNotNull().isInstanceOf(ClassSimple.class);
            assertThat(singletonA).isSameAs(singletonB);
        }

    }

}
