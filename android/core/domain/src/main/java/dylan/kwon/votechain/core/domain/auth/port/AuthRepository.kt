package dylan.kwon.votechain.core.domain.auth.port

import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val isInitializedSimplePassword: Flow<Boolean>

    suspend fun updateSimplePassword(password: SimplePassword)

    suspend fun authSimplePassword(password: SimplePassword): Boolean

}