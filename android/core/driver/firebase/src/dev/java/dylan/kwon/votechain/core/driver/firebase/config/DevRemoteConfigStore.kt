package dylan.kwon.votechain.core.driver.firebase.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DevRemoteConfigStore @Inject constructor() : DefaultRemoteConfigStore() {

    private object Key {
        const val RPC_URL = "RPC_URL_DEV"
        const val CONTRACT_ADDRESS = "CONTRACT_ADDRESS_DEV"
    }

    private val remoteConfig = Firebase.remoteConfig

    override val rpcUrl: String
        get() = remoteConfig[Key.RPC_URL].asString()

    override val contractAddress: String
        get() = remoteConfig[Key.CONTRACT_ADDRESS].asString()

}