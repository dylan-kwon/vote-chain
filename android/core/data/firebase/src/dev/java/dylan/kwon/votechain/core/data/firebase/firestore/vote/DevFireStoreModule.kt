package dylan.kwon.votechain.core.data.firebase.firestore.vote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DevFireStoreModule {

    @Provides
    fun provideDevFireStore(): FirebaseFirestore =
        Firebase.firestore("test")


}