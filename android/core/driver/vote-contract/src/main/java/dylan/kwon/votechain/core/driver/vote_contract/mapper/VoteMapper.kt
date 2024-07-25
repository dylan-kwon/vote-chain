package dylan.kwon.votechain.core.driver.vote_contract.mapper

import dylan.kwon.votechain.core.driver.vote_contract.model.Vote
import org.web3j.tuples.generated.Tuple9
import java.math.BigInteger

typealias Web3jVote = Tuple9<BigInteger, String, String, String, String, BigInteger, Boolean, BigInteger, Boolean>

internal fun Web3jVote.toVote() =
    Vote(
        id = component1().toLong(),
        owner = component2(),
        title = component3(),
        content = component4(),
        imageUrl = component5(),
        voterCount = component6().toLong(),
        isAllowDuplicateVoting = component7(),
        createdAt = component8().toLong(),
        isClosed = component9(),
    )