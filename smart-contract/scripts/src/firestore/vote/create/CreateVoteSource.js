const [
    dbName,
    id,
    title,
    content,
    imageUrl,
    createdAt,
    isClosed
] = args;

const {
    firebaseApiKey,
    email,
    password
} = secrets;

if (!dbName || !id || !title || !content || !imageUrl || !createdAt || !isClosed) {
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

const createVoteResponse = await Functions.makeHttpRequest({
    method: 'POST',
    url: `https://firestore.googleapis.com/v1/projects/vote-chain-e92b0/databases/${dbName}/documents/Votes`,
    headers: {
        Authorization: `Bearer ${data.idToken}`
    },
    params: {
        documentId: id
    },
    data: {
        fields: {
            id: {
                integerValue: id,
            },
            title: {
                stringValue: title,
            },
            content: {
                stringValue: content,
            },
            imageUrl: {
                stringValue: imageUrl,
            },
            createdAt: {
                integerValue: createdAt,
            },
            isClosed: {
                booleanValue: isClosed
            }
        }
    }
});

if (createVoteResponse.error) {
    if (createVoteResponse.code !== '409') {
        throw Error(`Create Vote Error: ${JSON.stringify(createVoteResponse)}`);
    }
}

return Functions.encodeString(true.toString());