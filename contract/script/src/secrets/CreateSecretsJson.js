const {
    SecretsManager,
} = require('@chainlink/functions-toolkit')

const ethers = require('ethers')
const fs = require('fs')
const path = require('path')

require('@chainlink/env-enc').config()

const privateKey = process.env.PRIVATE_KEY
const rpcUrl = process.env.ETHEREUM_RPC_URL
const routerAddress = process.env.ROUTER_ADDRESS
const donId = process.env.DON_ID

const secrets = {
    firebaseApiKey: process.env.FIREBASE_API_KEY,
    email: process.env.EMAIL,
    password: process.env.PASSWORD
}

const run = async () => {
    const signer = createSigner()
    console.log('✅ Signer: Ok')

    const secretsManager = await createSecretsManager(signer)
    console.log('✅ Create Secrets Manager: Ok')

    const encryptedSecrets = await encryptSecrets(secretsManager)
    console.log('✅ Encrypt Secrets: Ok')

    const jsonFile = exportSecretJson(encryptedSecrets)
    console.log('✅ Export Secrets: Ok', jsonFile)
}

function createSigner() {
    const provider = new ethers.providers.JsonRpcProvider(rpcUrl)
    const wallet = new ethers.Wallet(privateKey)
    return wallet.connect(provider)
}

async function createSecretsManager(signer) {
    const secretsManager = new SecretsManager({
        signer: signer,
        functionsRouterAddress: routerAddress,
        donId: donId,
    })
    await secretsManager.initialize();
    return secretsManager
}

async function encryptSecrets(secretsManager) {
    return await secretsManager.encryptSecrets(secrets)
}

function exportSecretJson(encryptedSecrets) {
    const rootDir = process.cwd()
    const secretsFilePath = path.resolve(rootDir, 'offchain-secrets.json')
    fs.writeFileSync(secretsFilePath, JSON.stringify(encryptedSecrets))
    return secretsFilePath
}

run().catch((e) => {
    console.error('❌ Error', e)
    process.exit(1)
})