package dylan.kwon.votechain.core.data.firebase.storage

interface VoteFirebaseStorage {

    suspend fun upload(uri: String): String

}