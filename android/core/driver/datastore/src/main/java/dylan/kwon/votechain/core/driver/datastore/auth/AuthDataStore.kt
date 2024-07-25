package dylan.kwon.votechain.core.driver.datastore.auth

import kotlinx.coroutines.flow.Flow

interface AuthDataStore {
    val simplePassword: Flow<String>

    suspend fun updateSimplePassword(simplePassword: String)
}