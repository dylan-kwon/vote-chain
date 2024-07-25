package dylan.kwon.voetchain.core.data.bundle.cloudStorage

import dylan.kwon.votechain.core.driver.firebase.storage.VoteFirebaseStorage
import dylan.kwon.votechain.core.domain.cloudStorage.CloudStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCloudStorage @Inject constructor(
    private val voteFirebaseStorage: VoteFirebaseStorage
) : CloudStorage {

    override suspend fun upload(uri: String): String {
        return voteFirebaseStorage.upload(uri)
    }

}