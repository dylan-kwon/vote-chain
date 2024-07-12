package dylan.kwon.votechain.core.coroutine.test.dispatcher

import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestDispatcherProvider @Inject constructor(
    testDispatcher: TestDispatcher
) : DispatcherProvider {
    override val io: CoroutineDispatcher = testDispatcher

    override val main: CoroutineDispatcher = testDispatcher

    override val default: CoroutineDispatcher = testDispatcher

    override val unconfined: CoroutineDispatcher = testDispatcher
}