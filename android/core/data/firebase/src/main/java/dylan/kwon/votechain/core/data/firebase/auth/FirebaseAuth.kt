package dylan.kwon.votechain.core.data.firebase.auth

interface FirebaseAuth {

    suspend fun isLogin(): Boolean

    suspend fun auth(): Boolean

}