package dylan.kwon.votechain.core.domain.cryptoWallet.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import javax.inject.Inject

class LoadCryptoWalletUseCase @Inject constructor(
    private val cryptoWalletRepository: CryptoWalletRepository
) : UseCase<Mnemonic, Unit>() {
    override suspend fun onInvoke(input: Mnemonic) = with(cryptoWalletRepository) {
        saveCryptoWallet(
            loadCryptoWallet(input)
        )
    }
}