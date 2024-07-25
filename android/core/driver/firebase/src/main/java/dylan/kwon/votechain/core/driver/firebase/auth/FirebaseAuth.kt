package dylan.kwon.votechain.core.driver.firebase.auth

interface FirebaseAuth {

    suspend fun isLogin(): Boolean

    suspend fun auth(): Boolean

}