const {
    SecretsManager,
} = require('@chainlink/functions-toolkit')

require('@chainlink/env-enc').config()

const ethers = require('ethers')
const privateKey = process.env.PRIVATE_KEY
const rpcUrl = process.env.ETHEREUM_RPC_URL
const routerAddress = process.env.ROUTER_ADDRESS
const donId = process.env.DON_ID

const secretsUrls = [
    process.argv[2]
]

console.log(secretsUrls)

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