package dylan.kwon.votechain.core.domain.cryptoWallet.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import javax.inject.Inject

class CreateMnemonicUseCase @Inject constructor(
    private val cryptoWalletPort: CryptoWalletPort
) : UseCase<Unit, Mnemonic>() {
    override suspend fun onInvoke(input: Unit): Mnemonic {
        return cryptoWalletPort.createMnemonic()
    }
}