package dylan.kwon.votechain.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance

class FirebaseInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Firebase.performance.isPerformanceCollectionEnabled = !BuildConfig.DEBUG
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf()
    }
}