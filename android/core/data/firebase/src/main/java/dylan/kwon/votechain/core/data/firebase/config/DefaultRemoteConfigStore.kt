package dylan.kwon.votechain.core.data.firebase.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteConfigStore @Inject constructor() : RemoteConfigStore {

    object Key {
        const val RPC_URL = "RPC_URL"
        const val CONTRACT_ADDRESS = "CONTRACT_ADDRESS"
    }

    private val remoteConfig = Firebase.remoteConfig

    override val isFetched: Boolean
        get() = rpcUrl.isNotEmpty() && contractAddress.isNotEmpty()

    override val rpcUrl: String
        get() = remoteConfig[Key.RPC_URL].asString()

    override val contractAddress: String
        get() = remoteConfig[Key.CONTRACT_ADDRESS].asString()

    override suspend fun fetch(): Boolean {
        return remoteConfig.fetchAndActivate().await()
    }

}