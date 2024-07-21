package dylan.kwon.voetchain.core.data.bundle.config

import dylan.kwon.votechain.core.data.firebase.config.RemoteConfigStore
import dylan.kwon.votechain.core.domain.config.model.Config
import dylan.kwon.votechain.core.domain.config.port.ConfigRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultConfigRepository @Inject constructor(
    private val remoteConfigStore: RemoteConfigStore
) : ConfigRepository {

    override suspend fun isReady(): Boolean {
        return remoteConfigStore.isFetched
    }

    override suspend fun setup(): Boolean {
        return remoteConfigStore.fetch()
    }

    override suspend fun getConfig(): Config = Config(
        rpcUrl = remoteConfigStore.rpcUrl,
        contractAddress = remoteConfigStore.contractAddress,
    )

}