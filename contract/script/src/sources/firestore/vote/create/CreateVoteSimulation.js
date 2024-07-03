const fs = require('fs');
const path = require('path');
const {
    simulateScript
} = require('@chainlink/functions-toolkit');

require('@chainlink/env-enc').config();

const createdAt = new Date().getTime()
const newId = createdAt.toString()

const args = [
    "test",                                                     // dbName
    newId,                                                      // voteId
    `Title: ${newId}`,                                          // title
    `Content: ${newId}`,                                        // content
    'https://img.hankyung.com/photo/202401/03.35225885.1.jpg',  // imageUrl
    newId,                                                      // createdAt
    'false'                                                     // isClosed
]

const secrets = {
    firebaseApiKey: process.env.FIREBASE_API_KEY,
    email: process.env.EMAIL,
    password: process.env.PASSWORD
};

const source = fs
    .readFileSync(path.resolve(__dirname, 'CreateVoteSource.js'))
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

    console.log('✅ New Vote ID: ', newId);
    console.log('✅ Simulation Ok: ', response);
}

run().catch((e) => {
    console.error(e);
    process.exit(1);
});