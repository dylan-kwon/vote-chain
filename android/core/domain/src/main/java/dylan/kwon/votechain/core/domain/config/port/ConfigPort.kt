package dylan.kwon.votechain.core.domain.config.port

import dylan.kwon.votechain.core.domain.config.model.Config

interface ConfigPort {

    suspend fun setup(): Boolean

    suspend fun isReady(): Boolean

    suspend fun getConfig(): Config

}