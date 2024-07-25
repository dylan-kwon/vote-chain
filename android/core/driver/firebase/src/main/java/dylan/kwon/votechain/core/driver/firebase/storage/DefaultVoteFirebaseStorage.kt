package dylan.kwon.votechain.core.driver.firebase.storage

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DefaultVoteFirebaseStorage @Inject constructor(
    private val storage: FirebaseStorage
) : VoteFirebaseStorage {

    private val auth = Firebase.auth

    private val ref by lazy {
        storage.reference.child("Vote")
    }

    override suspend fun upload(uri: String): String {
        val toUri = Uri.parse(uri)
        val fileName = "${auth.uid}_${System.currentTimeMillis()}"
        val updateRef = ref.child(fileName).apply {
            putFile(toUri).await()
        }
        return updateRef.downloadUrl.await().toString()
    }

}