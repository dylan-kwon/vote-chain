package dylan.kwon.votechain.core.domain.setup.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.auth.port.AuthRepository
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import dylan.kwon.votechain.core.domain.setup.entity.AppSetupResult
import javax.inject.Inject

class AppSetupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val cryptoWalletRepository: CryptoWalletRepository,
) : UseCase<Unit, AppSetupResult>() {

    override suspend fun onInvoke(input: Unit): AppSetupResult {
        if (!checkAuth()) {
            return AppSetupResult.AuthError
        }
        if (!checkCryptoWallet()) {
            return AppSetupResult.NeedCryptoWallet
        }
        return AppSetupResult.Completed
    }

    private suspend fun checkAuth(): Boolean {
        return authRepository.auth()
    }

    private suspend fun checkCryptoWallet(): Boolean {
        return cryptoWalletRepository.hasCryptoWallet()
    }

}