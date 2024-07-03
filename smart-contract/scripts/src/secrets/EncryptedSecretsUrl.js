const {
    SecretsManager,
} = require('@chainlink/functions-toolkit')

require('@chainlink/env-enc').config()

const ethers = require('ethers')
const privateKey = process.env.PRIVATE_KEY
const rpcUrl = process.env.ETHEREUM_SEPOLIA_RPC_URL
const routerAddress = '0x234a5fb5Bd614a7AA2FfAB244D603abFA0Ac5C5C'
const donId = 'fun-arbitrum-sepolia-1'
const donIds = 'fun-arbitrum-sepolia-1'

const secretsUrls = [
    "https://firebasestorage.googleapis.com/v0/b/vote-chain-e92b0.appspot.com/o/Configs%2FChainLink%2Foffchain-secrets.json?alt=media&token=ee4a4943-e5df-4425-8471-9bc6a259238a"
]

const run = async () => {
    const signer = createSigner()
    console.log('✅ Signer: Ok')

    const secretsManager = await createSecretsManager(signer)
    console.log('✅ Create Secrets Manager: Ok')

    const encryptedSecretsUrl = await encryptSecretsUrls(secretsManager)
    console.log('✅ Encrypted URL: Ok', encryptedSecretsUrl)
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

async function encryptSecretsUrls(secretsManager) {
    return await secretsManager.encryptSecretsUrls(secretsUrls)
}

run().catch((e) => {
    console.error('❌ Error', e)
    process.exit(1)
})