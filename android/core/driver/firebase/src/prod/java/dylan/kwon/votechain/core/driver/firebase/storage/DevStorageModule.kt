package dylan.kwon.votechain.core.driver.firebase.storage

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DevStorageModule {

    @Provides
    fun provideDevFireStore(): FirebaseStorage =
        Firebase.storage

}