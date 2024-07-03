## Summary

This document explains how to encrypt the [environment]((../../README.md)) and securely pass it to ChainLink Functions.

> This project uses Chainlink's' [Off-chain Hosted](https://github.com/smartcontractkit/functions-toolkit?tab=readme-ov-file#off-chain-hosted-secrets) Secrets.

## Create Secrets Json

`.env.enc` is encrypted twice using ChainLink's DON public key and the Threshold public key.

```
$ node .{Path}/CreateSecretsJson.js
```

> Create `.offchain-secrets.json` in the project root

## Upload Secrets Json
Upload the encrypted secrets JSON to Firebase Storage or Amazon S3, and copy the URL.


## URL Encryption

Run the following command to encrypt the URL and pass it to the SecretsUrls in the [smart contract](../../../).

```
$ node .{Path}/EncryptedSecretsUrl.js "${COPIED_URL}"
```

> Example: 0x81422 .. a6d74