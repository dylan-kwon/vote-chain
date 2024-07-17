package dylan.kwon.votechain.core.domain.auth.port

import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val isSetupSimplePassword: Flow<Boolean>

    suspend fun auth(): Boolean

    suspend fun updateSimplePassword(password: SimplePassword)

    suspend fun authSimplePassword(password: SimplePassword): Boolean

}