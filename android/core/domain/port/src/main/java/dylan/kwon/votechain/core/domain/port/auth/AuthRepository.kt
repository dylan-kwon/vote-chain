package dylan.kwon.votechain.core.domain.port.auth

import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val isInitializedSimplePassword: Flow<Boolean>

    suspend fun updateSimplePassword(password: SimplePassword)

    suspend fun authSimplePassword(password: SimplePassword): Boolean

}