package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cloudStorage.CloudStorage
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import dylan.kwon.votechain.core.domain.vote.entity.VoteForm
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CreateVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
    private val cryptoWalletRepository: CryptoWalletRepository,
    private val cloudStorage: CloudStorage
) : UseCase<VoteForm, Unit>() {

    override suspend fun onInvoke(input: VoteForm) = coroutineScope {
        // 1. Validate VoteForm
        checkValid(input)

        // 2. Upload Image and Load CryptoWallet
        val (imageUrl, cryptoWallet) = fetchData(input)

        // 3. Create vote
        voteRepository.createVote(
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
                    else -> cloudStorage.upload(voteForm.imageUri)
                }

            },
            // Load Crypto Wallet
            async {
                cryptoWalletRepository.getSavedCryptoWallet()
            },
        )
        val imageUrl = result.firstOrNull() as? String
        val cryptoWallet = result.last() as CryptoWallet

        imageUrl to cryptoWallet
    }
}