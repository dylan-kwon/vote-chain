package dylan.kwon.votechain.core.data.vote_contract.mapper

import dylan.kwon.votechain.core.data.vote_contract.model.Vote
import org.web3j.tuples.generated.Tuple8
import java.math.BigInteger

internal fun Tuple8<BigInteger, String, String, String, String, Boolean, BigInteger, Boolean>.toVote() =
    Vote(
        id = component1(),
        owner = component2(),
        title = component3(),
        content = component4(),
        imageUrl = component5(),
        isAllowDuplicateVoting = component6(),
        createdAt = component7(),
        isClosed = component8(),
    )