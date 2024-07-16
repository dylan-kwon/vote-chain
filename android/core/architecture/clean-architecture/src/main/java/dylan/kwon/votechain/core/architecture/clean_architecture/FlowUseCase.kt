package dylan.kwon.votechain.core.architecture.clean_architecture

import kotlinx.coroutines.flow.Flow


abstract class FlowUseCase<Input, Output> {

    operator fun invoke(input: Input): Flow<Output> = onInvoke(input)

    abstract fun onInvoke(input: Input): Flow<Output>

}