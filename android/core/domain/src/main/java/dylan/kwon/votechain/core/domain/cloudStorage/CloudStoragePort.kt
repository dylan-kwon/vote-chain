package dylan.kwon.votechain.core.domain.cloudStorage

interface CloudStoragePort {

    suspend fun upload(uri: String): String

}