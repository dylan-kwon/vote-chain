package dylan.kwon.votechain.core.domain.cloudStorage

interface CloudStorage {

    suspend fun upload(uri: String): String

}