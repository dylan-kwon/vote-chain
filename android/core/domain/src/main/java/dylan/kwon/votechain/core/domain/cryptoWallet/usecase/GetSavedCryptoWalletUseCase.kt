package dylan.kwon.votechain.core.domain.cryptoWallet.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import javax.inject.Inject

class GetSavedCryptoWalletUseCase @Inject constructor(
    private val cryptoWalletPort: CryptoWalletPort
) : UseCase<Unit, CryptoWallet>() {
    override suspend fun onInvoke(input: Unit): CryptoWallet {
        return cryptoWalletPort.getSavedCryptoWallet()
    }
}