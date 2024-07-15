package dylan.kwon.votechain.core.domain.cryptoWallet.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.SuspendUseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import javax.inject.Inject

class CreateMnemonicUseCase @Inject constructor(
    private val cryptoWalletRepository: CryptoWalletRepository
) : SuspendUseCase<Unit, Mnemonic>() {
    override suspend fun onInvoke(input: Unit): Mnemonic {
        return cryptoWalletRepository.createMnemonic()
    }
}