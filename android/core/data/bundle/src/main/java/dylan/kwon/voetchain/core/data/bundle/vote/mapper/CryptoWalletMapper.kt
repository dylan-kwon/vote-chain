package dylan.kwon.voetchain.core.data.bundle.vote.mapper

import dylan.kwon.votechain.core.data.vote_contract.model.Credential
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet

internal fun CryptoWallet.toCredential(): Credential = Credential(
    public = publicKey,
    private = privateKey,
    address = address
)