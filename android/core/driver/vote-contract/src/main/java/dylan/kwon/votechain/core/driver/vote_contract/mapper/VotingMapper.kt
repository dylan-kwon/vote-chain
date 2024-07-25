package dylan.kwon.votechain.core.driver.vote_contract.mapper

fun List<Int>.mapBigInteger() = map {
    it.toBigInteger()
}