package dylan.kwon.voetchain.core.adapter.auth

import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword
import dylan.kwon.votechain.core.domain.auth.port.AuthPort
import dylan.kwon.votechain.core.driver.datastore.auth.AuthDataStore
import dylan.kwon.votechain.core.driver.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthAdapter @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val authDataStore: AuthDataStore,
    private val firebaseAuth: FirebaseAuth,
) : AuthPort {

    override val isSetupSimplePassword: Flow<Boolean>
        get() = authDataStore.simplePassword.map {
            it.isNotEmpty()
        }

    override suspend fun auth(): Boolean {
        return withContext(dispatcherProvider.io) {
            firebaseAuth.auth()
        }
    }

    override suspend fun updateSimplePassword(password: SimplePassword) {
        withContext(dispatcherProvider.io) {
            authDataStore.updateSimplePassword(password.value)
        }
    }

    override suspend fun authSimplePassword(password: SimplePassword): Boolean {
        return withContext(dispatcherProvider.io) {
            authDataStore.simplePassword.firstOrNull() == password.value
        }
    }
}