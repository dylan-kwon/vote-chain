package dylan.kwon.voetchain.core.adapter.cloudStorage

import dylan.kwon.votechain.core.driver.firebase.storage.VoteFirebaseStorage
import dylan.kwon.votechain.core.domain.cloudStorage.CloudStoragePort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudStorageAdapter @Inject constructor(
    private val voteFirebaseStorage: VoteFirebaseStorage
) : CloudStoragePort {

    override suspend fun upload(uri: String): String {
        return voteFirebaseStorage.upload(uri)
    }

}