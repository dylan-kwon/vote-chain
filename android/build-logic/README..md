# Summary

Here, there is a plugin that defines build scripts commonly used across modules.

## Plugins

| Name                                                                                                                                  | Id                            | Description                            |
|:--------------------------------------------------------------------------------------------------------------------------------------|:------------------------------|:---------------------------------------|
| [AndroidApplicationPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/AndroidApplicationPlugin.kt) | votechain.jvm.library         | Common Script for Android Applications |
| [AndroidLibraryPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/AndroidLibraryPlugin.kt)         | votechain.android.library     | Common Script for Android Library      |
| [JvmLibraryPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/JvmLibraryPlugin.kt)                 | votechain.android.application | Common Script for JVM Library          |
| [ComposePlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/ComposePlugin.kt)                       | votechain.compose             | Add Compose Dependency                 |
| [HiltPlugin](./convention/src/main/java/dylan/kwon/votechain/build_logic/convention/plugin/HiltPlugin.kt)                             | votechain.hilt                | Add Hilt Dependency                    |


