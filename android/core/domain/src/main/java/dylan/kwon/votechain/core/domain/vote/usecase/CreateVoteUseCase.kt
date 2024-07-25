package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cloudStorage.CloudStoragePort
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import dylan.kwon.votechain.core.domain.vote.entity.VoteForm
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CreateVoteUseCase @Inject constructor(
    private val votePort: VotePort,
    private val cryptoWalletPort: CryptoWalletPort,
    private val cloudStoragePort: CloudStoragePort
) : UseCase<VoteForm, Unit>() {

    override suspend fun onInvoke(input: VoteForm) = coroutineScope {
        // 1. Validate VoteForm
        checkValid(input)

        // 2. Upload Image and Load CryptoWallet
        val (imageUrl, cryptoWallet) = fetchData(input)

        // 3. Create vote
        votePort.createVote(
            voteForm = input.copy(
                imageUri = imageUrl
            ),
            cryptoWallet = cryptoWallet
        )
    }

    private fun checkValid(voteForm: VoteForm) {
        if (!voteForm.isValid()) {
            throw IllegalArgumentException()
        }
    }

    private suspend fun fetchData(voteForm: VoteForm) = coroutineScope {
        val result = awaitAll(
            // Upload image if INPUT has imageUri
            async {
                when (voteForm.imageUri) {
                    null -> null
                    else -> cloudStoragePort.upload(voteForm.imageUri)
                }

            },
            // Load Crypto Wallet
            async {
                cryptoWalletPort.getSavedCryptoWallet()
            },
        )
        val imageUrl = result.firstOrNull() as? String
        val cryptoWallet = result.last() as CryptoWallet

        imageUrl to cryptoWallet
    }
}