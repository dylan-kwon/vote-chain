package dylan.kwon.votechain.core.driver.firebase.storage

interface VoteFirebaseStorage {

    suspend fun upload(uri: String): String

}