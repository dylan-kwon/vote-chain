package dylan.kwon.votechain.core.architecture.clean_architecture

abstract class UseCase<Input, Output> {

    suspend operator fun invoke(input: Input): Result<Output> = runCatching {
        onInvoke(input)
    }

    abstract suspend fun onInvoke(input: Input): Output

}