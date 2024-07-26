# Summary

Here, there is a plugin that defines build scripts commonly used across modules.

## Jvm

| Name                                                                                                                      | Id                    | Description                  |
|:--------------------------------------------------------------------------------------------------------------------------|:----------------------|:-----------------------------|
| [JvmLibraryPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/jvm/JvmLibraryPlugin.kt) | votechain.jvm.library | Common Script of Jvm Library |

## Android

| Name                                                                                                                                          | Id                            | Description                             |
|:----------------------------------------------------------------------------------------------------------------------------------------------|:------------------------------|:----------------------------------------|
| [AndroidApplicationPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidApplicationPlugin.kt) | votechain.android.application | Common Script of Android Applications   | 
| [AndroidLibraryPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidLibraryPlugin.kt)         | votechain.android.library     | Common Script of Android Library        | 
| [AndroidLifecyclePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidLifecyclePlugin.kt)     | votechain.android.lifecycle   | Common Script of Android Lifecycle      | 
| [AndroidComposePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidComposePlugin.kt)         | votechain.android.compose     | Common Script of Android Compose        | 
| [AndroidParcelizePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidParcelizePlugin.kt)     | votechain.android.parcelize   | Common Script of Android Parcelize      | 
| [AndroidDataStorePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidDataStorePlugin.kt)     | votechain.android.datastore   | Common Script of Android DataStore      | 
| [AndroidFeaturePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/android/AndroidFeaturePlugin.kt)         | votechain.android.feature     | Common Script of Android Feature Module | 

## Kotlin

| Name                                                                                                                                              | Id                                     | Description                            |
|:--------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------|:---------------------------------------|
| [CoroutinePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/kotlinx/CoroutinePlugin.kt)                       | votechain.kotlinx.coroutine            | Common Script of Coroutine             |
| [SerializationPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/kotlinx/SerializationPlugin.kt)               | votechain.kotlinx.serialization        | Common Script of Serialization         |
| [ImmutableCollectionsPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/kotlinx/ImmutableCollectionsPlugin.kt) | votechain.kotlinx.immutableCollections | Common Script of Immutable Collections |

## ETC

| Name                                                                                                                       | Id                 | Description               |
|:---------------------------------------------------------------------------------------------------------------------------|:-------------------|:--------------------------|
| [HiltPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/hilt/HiltPlugin.kt)             | votechain.hilt     | Common Script of Hilt     |
| [FirebasePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/firebase/FirebasePlugin.kt) | votechain.firebase | Common Script of Firebase |
