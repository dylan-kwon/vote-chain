package dylan.kwon.votechain.core.domain.cryptoWallet.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import javax.inject.Inject

class GetSavedCryptoWalletUseCase @Inject constructor(
    private val cryptoWalletRepository: CryptoWalletRepository
) : UseCase<Unit, CryptoWallet>() {
    override suspend fun onInvoke(input: Unit): CryptoWallet {
        return cryptoWalletRepository.getSavedCryptoWallet()
    }
}