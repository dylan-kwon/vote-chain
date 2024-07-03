const fs = require('fs');
const path = require('path');
const {
    simulateScript
} = require('@chainlink/functions-toolkit');

require('@chainlink/env-enc').config();

const id = process.argv[2]

const args = [
    "test",     // DbName
    id,         // voteId
]

const secrets = {
    firebaseApiKey: process.env.FIREBASE_API_KEY,
    email: process.env.EMAIL,
    password: process.env.PASSWORD
};

const source = fs
    .readFileSync(path.resolve(__dirname, 'CloseVoteSource.js'))
    .toString();

const run = async () => {
    const response = await simulateScript({
        source: source,
        args: args,
        bytesArgs: [],
        secrets: secrets,
    });

    if (response.errorString) {
        throw (response.errorString)
    }

    console.log('✅ Simulation Ok: ', response);
}

run().catch((e) => {
    console.error(e);
    process.exit(1);
});