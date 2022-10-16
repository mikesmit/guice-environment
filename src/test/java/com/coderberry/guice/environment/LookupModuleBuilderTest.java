package com.coderberry.guice.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LookupModuleBuilderTest {

    class Module{

    }

    @Test
    public void testAdd() {
        Module m = new Module();
        Function<Map<String, String>, Module> mockFactory =  Mockito.mock(Function.class);
        Mockito.when(mockFactory.apply(Mockito.anyMap())).thenReturn(m);
        Module result = new LookupModuleBuilder<Module>(mockFactory)
                .add("NAME", "NAME2")
                .build();

        Assertions.assertEquals(m, result);
        Map<String, String> expected = new HashMap<>();
        expected.put("NAME", "NAME");
        expected.put("NAME2", "NAME2");
        Mockito.verify(mockFactory).apply(expected);
    }
    @Test
    public void testPrefix() {
        Module m = new Module();
        Function<Map<String, String>, Module> mockFactory =  Mockito.mock(Function.class);
        Mockito.when(mockFactory.apply(Mockito.anyMap())).thenReturn(m);
        Module result = new LookupModuleBuilder<Module>(mockFactory)
                .prefix("Prefix.", "NAME", "NAME2")
                .build();

        Assertions.assertEquals(m, result);
        Map<String, String> expected = new HashMap<>();
        expected.put("Prefix.NAME", "NAME");
        expected.put("Prefix.NAME2", "NAME2");
        Mockito.verify(mockFactory).apply(expected);
    }

    @Test
    public void testRename() {
        Module m = new Module();
        Function<Map<String, String>, Module> mockFactory =  Mockito.mock(Function.class);
        Mockito.when(mockFactory.apply(Mockito.anyMap())).thenReturn(m);
        Module result = new LookupModuleBuilder<Module>(mockFactory)
                .rename("NAME", "TargetName")
                .build();

        Assertions.assertEquals(m, result);
        Map<String, String> expected = new HashMap<>();
        expected.put("TargetName", "NAME");
        Mockito.verify(mockFactory).apply(expected);
    }

}
