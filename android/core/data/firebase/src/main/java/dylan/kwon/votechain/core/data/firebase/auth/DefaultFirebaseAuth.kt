package dylan.kwon.votechain.core.data.firebase.auth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultFirebaseAuth @Inject constructor() : FirebaseAuth {

    override suspend fun isLogin(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override suspend fun auth(): Boolean {
        try {
            if (isLogin()) {
                return true
            }
            val signInResult = Firebase.auth.signInAnonymously().await()
            val user = signInResult.user

            return user != null

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}