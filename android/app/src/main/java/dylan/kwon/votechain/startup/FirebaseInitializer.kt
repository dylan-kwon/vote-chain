package dylan.kwon.votechain.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.PersistentCacheSettings
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FirebaseInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        initCrashlytics()
        initPerformance()
        initFirestore()
        initRemoteConfig()
    }

    private fun initCrashlytics() {
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    private fun initPerformance() {
        Firebase.performance.isPerformanceCollectionEnabled = !BuildConfig.DEBUG
    }

    private fun initFirestore() {
        Firebase.firestore.firestoreSettings = firestoreSettings {
            setLocalCacheSettings(PersistentCacheSettings.newBuilder().build())
        }
    }

    private fun initRemoteConfig() {
        val settings = remoteConfigSettings {
            if (BuildConfig.DEBUG) {
                minimumFetchIntervalInSeconds = 3600
            }
        }
        Firebase.remoteConfig.setConfigSettingsAsync(settings)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf()
    }
}