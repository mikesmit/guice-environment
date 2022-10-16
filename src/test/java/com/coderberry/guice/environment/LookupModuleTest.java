package com.coderberry.guice.environment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LookupModuleTest {
    @Test
    public void test() {
        Function<String, String> mockLookup = Mockito.mock(Function.class);
        Map<String, String> properties = new HashMap<>();
        properties.put("NAME1", "KEY1");
        properties.put("NAME2", "KEY2");

        Mockito.when(mockLookup.apply("KEY1")).thenReturn("VALUE1");
        Mockito.when(mockLookup.apply("KEY2")).thenReturn("VALUE2");

        LookupModule lookupModule = new LookupModule(properties, mockLookup);
        Injector injector = Guice.createInjector(lookupModule);

        Assertions.assertEquals("VALUE1",
                injector.getInstance(Key.get(String.class, Names.named("NAME1"))));
        Assertions.assertEquals("VALUE2",
                injector.getInstance(Key.get(String.class, Names.named("NAME2"))));
    }
}
