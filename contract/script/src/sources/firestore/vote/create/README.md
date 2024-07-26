## Overview

This section contains the source code for storing voting information in Firestore from the smart contract, as well as
simulation code for testing.

## [Source](./CreateVoteSource.js)

Copy the source code within the file and store it by calling the `constructor` or `setCreateVoteSource` of the smart
contract.

## [Simulation](./CreateVoteSimulation.js)

Perform local testing before storing in the smart contract.

```
$ node ./${PATH}/CreateVoteSimulation.js 
```

### Precautions

- All simulations run on the test database in Firestore. 

