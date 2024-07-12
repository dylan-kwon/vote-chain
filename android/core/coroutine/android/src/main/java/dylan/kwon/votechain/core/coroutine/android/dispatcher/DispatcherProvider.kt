package dylan.kwon.votechain.core.coroutine.android.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher

    val default: CoroutineDispatcher

    val unconfined: CoroutineDispatcher

}
