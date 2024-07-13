package dylan.kwon.votechain.core.architecture.clean_architecture

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


abstract class FlowUseCase<Input, Output> {

    operator fun invoke(input: Input): Flow<Result<Output>> = onInvoke(input)
        .map {
            Result.success(it)
        }.catch {
            emit(Result.failure(it))
        }

    abstract fun onInvoke(input: Input): Flow<Output>

}