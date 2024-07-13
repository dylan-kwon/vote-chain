package dylan.kwon.votechain.core.architecture.clean_architecture

abstract class UseCase<Input, Output> {

    operator fun invoke(input: Input): Result<Output> = runCatching {
        onInvoke(input)
    }

    abstract fun onInvoke(input: Input): Output

}