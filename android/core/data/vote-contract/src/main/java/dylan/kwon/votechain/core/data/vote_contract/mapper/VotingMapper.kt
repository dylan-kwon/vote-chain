package dylan.kwon.votechain.core.data.vote_contract.mapper

fun List<Int>.mapBigInteger() = map {
    it.toBigInteger()
}