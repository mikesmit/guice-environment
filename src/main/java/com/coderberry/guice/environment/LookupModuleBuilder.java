package com.coderberry.guice.environment;

import jdk.dynalink.linker.support.Lookup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LookupModuleBuilder<M> {
    private final Map<String, String> lookups = new HashMap<>();
    private final Function<Map<String, String>, M> factory;

    public LookupModuleBuilder(Function<Map<String, String>, M> factory) {
        this.factory = factory;
    }

    public LookupModuleBuilder<M> add(String ... keys) {
        Arrays.asList(keys).forEach(k->rename(k, k));
        return this;
    }

    public LookupModuleBuilder<M> prefix(String prefix, String ...keys){
        Arrays.asList(keys).stream().forEach(key->rename(key, prefix + key));
        return this;
    }

    public LookupModuleBuilder<M> rename(String key, String name) {
        lookups.put(name, key);
        return this;
    }

    public M build() {
        return factory.apply(this.lookups);
    }
}
