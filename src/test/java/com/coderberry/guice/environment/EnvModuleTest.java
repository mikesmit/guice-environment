package com.coderberry.guice.environment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnvModuleTest {

    @Test
    public void test() {
        EnvModule module = EnvModule.builder()
                .add("MAVEN_SET_TEST_ENV")
                .build();
        Injector injector = Guice.createInjector(module);

        String value = injector.getInstance(Key.get(String.class, Names.named("MAVEN_SET_TEST_ENV")));

        Assertions.assertEquals("TEST_VALUE", value);
    }
}
