# guice-environment
A lightweight library for binding environment variables and properties into your guice module

## Properties
Import a set of properties as named string providers
```java
PropModule propModule = PropModule.prefixAll("coderberryprops.", properties);

public class MyModule extends AbstractModule{
    @Override
    protected void configure(){
        install(propModule);
    }
    
    @Provides
    public Whatever provide(@Named("coderberryprops.propertyName") property) {
    ...
    }
}
```

Import a specific subset of properties
```java
PropModule module = PropModule.builder()
        .prefix("coderberryprops.", "propertyName1", "propertyName2")
        .rename("propertyName3", "coderBerryProps.bucketName")
        .build();
```
## Environment Variables
Import a specific subset of environment variables.
```java

EnvModule envModule = EnvModule.builder()
    .prefix("coderberryenv.", "ENV_VARIABLE1", "ENV_VARIABLE2")
    .rename("ENV_VARIABLE3", "coderBerryEnv.bucketName")
    .build();

public class MyModule extends AbstractModule{
    @Override
    protected void configure(){
        install(envModule);
    }

    @Provides
    public Whatever provide(
            @Named("coderberryenv.ENV_VARIABLE1") value1,
            @Named("coderBerryEnv.bucketName") bucketName
    ) {
    ...
    }
}
```