package dylan.kwon.votechain.core.driver.firebase.config

interface RemoteConfigStore {

    val isFetched: Boolean

    val rpcUrl: String

    val contractAddress: String

    suspend fun fetch(): Boolean

}