# VoteChain - Android

In Progress..

## KeyStore

Add the release and debug keyStores and properties.

1. `/${android-root}/keystore/{build-type}/{build-type}.keystore`
2. `/${android-root}/keystore/{build-type}/{build-type}-keystore.properties`

### KeyStore Properties

```properties
storeFile=..
keyAlias=..
keyPassword=..
storePassword=..
```

## Dependency Update

- Run the version update command

```
 $ ./gradlew refreshVersions 
```

- Uncomment the desired version in Update libs.versions.toml

## Convention Plugin

[Here](./build-logic)