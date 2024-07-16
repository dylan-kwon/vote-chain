package dylan.kwon.votechain.core.data.datastore.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.osipxd.security.crypto.encryptedPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val NAME = "auth"

private val Context.authDataStore by encryptedPreferencesDataStore(name = NAME)

@Singleton
class DefaultAuthDataStore @Inject constructor(
    @ApplicationContext
    private val applicationContext: Context
) : AuthDataStore {

    private object Key {
        val SIMPLE_PASSWORD = stringPreferencesKey("simple-password")
    }

    override val simplePassword: Flow<String> = applicationContext.authDataStore.data
        .map { prefs ->
            prefs[Key.SIMPLE_PASSWORD] ?: ""
        }

    override suspend fun updateSimplePassword(simplePassword: String) {
        applicationContext.authDataStore.edit { prefs ->
            prefs[Key.SIMPLE_PASSWORD] = simplePassword
        }
    }
}