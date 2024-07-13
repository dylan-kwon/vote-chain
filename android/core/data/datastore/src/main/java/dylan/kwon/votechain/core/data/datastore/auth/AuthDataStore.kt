package dylan.kwon.votechain.core.data.datastore.auth

import kotlinx.coroutines.flow.Flow

interface AuthDataStore {
    val simplePassword: Flow<String>

    suspend fun updateSimplePassword(simplePassword: String)
}