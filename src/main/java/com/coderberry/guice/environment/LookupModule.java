package com.coderberry.guice.environment;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.util.Map;
import java.util.function.Function;

public class LookupModule extends AbstractModule {
    private final Map<String, String> nameToLookup;
    private final Function<String, String> propertyLookupStrategy;

    public LookupModule(final Map<String, String> nameToLookup, Function<String, String> propertyLookupStrategy) {
        this.nameToLookup = nameToLookup;
        this.propertyLookupStrategy = propertyLookupStrategy;
    }

    @Override
    protected void configure() {
        nameToLookup.entrySet().stream()
                .forEach(entry->bind(String.class)
                        .annotatedWith(Names.named(entry.getKey()))
                        .toProvider(()->{
                            return propertyLookupStrategy.apply(entry.getValue());
                        })
                );
    }

    protected String getenv(String name) {
        return System.getenv(name);
    }
}
