package com.coderberry.guice.environment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class PropModuleTest {
    @Test
    public void test() {
        final Properties properties = new Properties();
        properties.setProperty("PROP1", "VALUE1");
        properties.setProperty("PROP2", "VALUE2");

        PropModule module = PropModule.build(properties)
                .add("PROP1")
                .rename("PROP2", "name2")
                .build();
        Injector injector = Guice.createInjector(module);

        Assertions.assertEquals("VALUE1",
                injector.getInstance(Key.get(String.class, Names.named("PROP1"))));
        Assertions.assertEquals("VALUE2",
                injector.getInstance(Key.get(String.class, Names.named("name2"))));
    }

    @Test
    public void all() {
        final Properties properties = new Properties();
        properties.setProperty("PROP1", "VALUE1");
        properties.setProperty("PROP2", "VALUE2");

        PropModule module = PropModule.prefixAll("coderberry.", properties);

        Injector injector = Guice.createInjector(module);

        Assertions.assertEquals("VALUE1",
                injector.getInstance(Key.get(String.class, Names.named("coderberry.PROP1"))));
        Assertions.assertEquals("VALUE2",
                injector.getInstance(Key.get(String.class, Names.named("coderberry.PROP2"))));
    }
}
