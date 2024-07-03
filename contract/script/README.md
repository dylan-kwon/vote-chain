## Summary

This section includes the following code:

- Source code runnable on ChainLink Functions
- A simulation to test locally before uploading to ChainLink
- Encryption methods to secure sensitive data like API keys

## Sources

| Name                                            | Description                   |
|:------------------------------------------------|:------------------------------|
| [CreateVoteSource](src/sources/firestore/vote/create) | Stores vote data in Firestore |
| [CloseVoteSource](src/sources/firestore/vote/close)   | Closing the voting            |

## Secrets

| Name                                 | Description                                                                |
|:-------------------------------------|:---------------------------------------------------------------------------|
| [CreateSecretsJson](./src/secrets)   | Encrypts environment variables into a format that ChainLink can understand |
| [EncryptedSecretsUrl](./src/secrets) | Encrypts the URL into a format that ChainLink can understand               |

## Environment

To use the script, follow these steps

### 1. NPM Install

```
$ npm install
```

> Create `node_modules` in the project root

### 2. Create Environment

```
$ npx env-enc set-pw
```

> Enter the password for encrypting the environment variables

```
$ npx env-enc set
```

> Enter the key and value from the following table

| Key              | Description                                                                                                                                                                                  |
|:-----------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| EMAIL            | Email of the [Authentication](https://firebase.google.com/docs/auth) account with write permissions to Firestore                                                                             |
| PASSWORD         | Password for the registered email account                                                                                                                                                    |
| FIREBASE_API_KEY | [Firebase api key](https://firebase.google.com/docs/projects/api-keys?authuser=3&_gl=1*17oki7k*_ga*OTk4OTQ2NDU3LjE3MTEwOTI1NjA.*_ga_CW55HF8NVT*MTcxOTk3MzIwNC4xMDMuMS4xNzE5OTczMjYzLjEuMC4w) |
| PRIVATE_KEY      | Private key of the crypto wallet                                                                                                                                                             |
| ETHEREUM_RPC_URL | RPC URL of the network to be used                                                                                                                                                            |
| ROUTER_ADDRESS   | [Router Address](https://docs.chain.link/chainlink-functions/supported-networks) of the network to be used                                                                                   |
| DON_ID           | [DON ID](https://docs.chain.link/chainlink-functions/supported-networks)  of the network to be used                                                                                          |

> Create `.env.enc` in the project root

> Note: This project
> uses [Arbitrum Sepolia](https://docs.chain.link/chainlink-functions/supported-networks#arbitrum-sepolia-testnet)

```
$ npx env-enc view
```

> Check the entered environment variables