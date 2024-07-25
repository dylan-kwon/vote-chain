package dylan.kwon.votechain.core.data.firebase.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.tasks.await

abstract class DefaultRemoteConfigStore : RemoteConfigStore {

    private val remoteConfig = Firebase.remoteConfig

    override val isFetched: Boolean
        get() = rpcUrl.isNotEmpty() && contractAddress.isNotEmpty()

    override suspend fun fetch(): Boolean {
        return remoteConfig.fetchAndActivate().await()
    }

}