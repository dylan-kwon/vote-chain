package dylan.kwon.votechain.core.coroutine.jvm.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher

    val default: CoroutineDispatcher

    val unconfined: CoroutineDispatcher

}
