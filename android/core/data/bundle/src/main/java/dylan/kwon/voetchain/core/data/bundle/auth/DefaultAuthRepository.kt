package dylan.kwon.voetchain.core.data.bundle.auth

import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.data.datastore.auth.AuthDataStore
import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword
import dylan.kwon.votechain.core.domain.auth.port.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAuthRepository @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val authDataStore: AuthDataStore
) : dylan.kwon.votechain.core.domain.auth.port.AuthRepository {

    override val isInitializedSimplePassword: Flow<Boolean>
        get() = authDataStore.simplePassword.map {
            it.isNotEmpty()
        }

    override suspend fun updateSimplePassword(password: dylan.kwon.votechain.core.domain.auth.entity.SimplePassword) {
        withContext(dispatcherProvider.io) {
            authDataStore.updateSimplePassword(password.value)
        }
    }

    override suspend fun authSimplePassword(password: dylan.kwon.votechain.core.domain.auth.entity.SimplePassword): Boolean {
        return withContext(dispatcherProvider.io) {
            authDataStore.simplePassword.firstOrNull() == password.value
        }
    }
}