package com.coderberry.guice.environment;

import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

public class PropModule extends LookupModule{
    public static class Builder extends LookupModuleBuilder<PropModule>{
        public Builder(final Properties properties) {
            super((map)->new PropModule(map, properties));
        }
    }

    public static Builder build(Properties props) {
        return new Builder(props);
    }

    public static PropModule all(Properties props) {
        return prefixAll("", props);
    }

    public static PropModule prefixAll(String prefix, Properties props) {
        return new Builder(props)
                .prefix(prefix, props.stringPropertyNames().toArray(new String[props.stringPropertyNames().size()]))
                .build();
    }
    public PropModule(final Map<String, String> nameToLookup, final Properties properties) {
        super(nameToLookup, properties::getProperty);
    }
}
