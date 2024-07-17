package dylan.kwon.votechain.core.data.firebase.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseAuthModule {

    @Binds
    abstract fun bindsDefaultFirebaseAuth(
        firebaseAuth: DefaultFirebaseAuth
    ): FirebaseAuth

}