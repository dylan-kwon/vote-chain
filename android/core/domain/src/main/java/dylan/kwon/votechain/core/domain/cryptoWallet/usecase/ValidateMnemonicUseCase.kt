package dylan.kwon.votechain.core.domain.cryptoWallet.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import javax.inject.Inject

class ValidateMnemonicUseCase @Inject constructor() : UseCase<Mnemonic, Boolean>() {
    override suspend fun onInvoke(input: Mnemonic): Boolean {
        return input.isValid
    }
}