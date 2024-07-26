# Build

This document explains the prerequisites for building the project

## 1. Create Keystore and Properties

Add the release and debug keyStores and properties.

### Release
```
./${ANDROID_ROOT}/keystore/{build-type}/{build-type}.keystore
```

### Debug
```
./${ANDROID_ROOT}/keystore/{build-type}/{build-type}-keystore.properties
```

### KeyStore Properties

The keystore properties include the following information

```properties
storeFile=..
keyAlias=..
keyPassword=..
storePassword=..
```

## 2. Create Smart Contract from ABI
```
$ cd ./#{ANDROID_ROOT}/core/driver/vote-contract
```
```
web3j generate solidity -b ./abi/VoteContract.bin -a ./abi/VoteContractImpl.abi -o ./src/main/java/ -p dylan.kwon.votechain.core.driver.vote_contract
```
> Output: ./src/main/java/dylan/kwon/votechain/core/driver/vote_contract/VoteContractImpl.java
