package dylan.kwon.voetchain.core.adapter.config

import dylan.kwon.votechain.core.driver.firebase.config.RemoteConfigStore
import dylan.kwon.votechain.core.domain.config.model.Config
import dylan.kwon.votechain.core.domain.config.port.ConfigPort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigAdapter @Inject constructor(
    private val remoteConfigStore: RemoteConfigStore
) : ConfigPort {

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