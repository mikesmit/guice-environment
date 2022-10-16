package com.coderberry.guice.environment;

import java.util.Map;
import java.util.function.Function;

public class EnvModule extends LookupModule{
    public static class Builder extends LookupModuleBuilder<EnvModule>{
        public Builder() {
            super((map)->new EnvModule(map));
        }
    }

    public static Builder builder() {
        return new Builder();
    }
    public EnvModule(Map<String, String> nameToLookup) {
        super(nameToLookup, System::getenv);
    }
}
