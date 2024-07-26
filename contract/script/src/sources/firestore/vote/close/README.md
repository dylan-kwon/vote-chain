## Overview

This section contains the source code for closing voting from the smart contract, as well as
simulation code for testing.

## [Source](./CloseVoteSource.js)

Copy the source code within the file and store it by calling the `constructor` or `setCloseVoteSource` of the smart
contract.

## [Simulation](./CloseVoteSimulation.js)

Perform local testing before storing in the smart contract.

```
$ node ./${PATH}/CloseVoteSimulation.js ${VOTE_ID} 
```

### Precautions

- All simulations run on the test database in Firestore. 

