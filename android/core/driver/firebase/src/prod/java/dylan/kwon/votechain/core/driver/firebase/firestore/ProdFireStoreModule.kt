package dylan.kwon.votechain.core.driver.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProdFireStoreModule {

    @Provides
    fun provideProdFireStore(): FirebaseFirestore =
        Firebase.firestore


}