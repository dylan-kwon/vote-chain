package dylan.kwon.votechain.core.domain.vote.entity

import dylan.kwon.votechain.core.domain.config.model.Config

data class VoteContractInfo(
    val rpcUrl: String,
    val contractAddress: String,
)

internal fun Config.toContractInfo() = VoteContractInfo(
    rpcUrl = rpcUrl,
    contractAddress = contractAddress
)