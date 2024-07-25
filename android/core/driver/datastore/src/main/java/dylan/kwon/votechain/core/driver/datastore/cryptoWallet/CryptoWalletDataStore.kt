package dylan.kwon.votechain.core.driver.datastore.cryptoWallet

import kotlinx.coroutines.flow.Flow

/**
 * Key Type is HexString.
 */
interface CryptoWalletDataStore {

    val publicKey: Flow<String>

    val privateKey: Flow<String>

    val address: Flow<String>

    suspend fun update(
        publicKey: String,
        privateKey: String,
        address: String
    )
}