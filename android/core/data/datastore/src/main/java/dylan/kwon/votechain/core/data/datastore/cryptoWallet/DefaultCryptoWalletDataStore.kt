package dylan.kwon.votechain.core.data.datastore.cryptoWallet

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.osipxd.security.crypto.encryptedPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val NAME = "crypto-wallet"

private val Context.cryptoWalletDataStore by encryptedPreferencesDataStore(name = NAME)

@Singleton
class DefaultCryptoWalletDataStore @Inject constructor(
    @ApplicationContext
    private val applicationContext: Context
) : CryptoWalletDataStore {

    private object Key {
        val PUBLIC_KEY = stringPreferencesKey("public-key")
        val PRIVATE_KEY = stringPreferencesKey("private-key")
        val ADDRESS = stringPreferencesKey("address")
    }

    private val dataStore
        get() = applicationContext.cryptoWalletDataStore

    override val publicKey: Flow<String> = dataStore.data
        .map { prefs ->
            prefs[Key.PUBLIC_KEY] ?: ""
        }

    override val privateKey: Flow<String> = dataStore.data
        .map { prefs ->
            prefs[Key.PRIVATE_KEY] ?: ""
        }

    override val address: Flow<String> = dataStore.data
        .map { prefs ->
            prefs[Key.ADDRESS] ?: ""
        }

    override suspend fun update(
        publicKey: String,
        privateKey: String,
        address: String
    ) {
        applicationContext.cryptoWalletDataStore.edit { prefs ->
            prefs[Key.PUBLIC_KEY] = publicKey
            prefs[Key.PRIVATE_KEY] = privateKey
            prefs[Key.ADDRESS] = address
        }
    }
}