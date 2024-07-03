const [
    dbName,
    id,
] = args;

const {
    firebaseApiKey,
    email,
    password
} = secrets;

if (!dbName || !id) {
    throw Error('Args Error');
}

if (!firebaseApiKey || !email || !password) {
    throw Error('Secret Error');
}

const signResponse = await Functions.makeHttpRequest({
    method: 'POST',
    url: 'https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword',
    params: {
        key: firebaseApiKey
    },
    data: {
        email: email,
        password: password,
        returnSecureToken: true
    }
});

if (signResponse.error) {
    throw Error(`Sign Error: ${JSON.stringify(signResponse)}`);
}

const {data} = signResponse;

const closeVoteResponse = await Functions.makeHttpRequest({
    method: 'PATCH',
    url: `https://firestore.googleapis.com/v1/projects/vote-chain-e92b0/databases/${dbName}/documents/Votes/${id}?updateMask.fieldPaths=isClosed`,
    headers: {
        Authorization: `Bearer ${data.idToken}`
    },
    data: {
        fields: {
            isClosed: {
                booleanValue: true
            }
        }
    }
});

if (closeVoteResponse.error) {
    throw Error(`Close Vote Error: ${JSON.stringify(closeVoteResponse)}`);
}

return Functions.encodeString(true.toString());