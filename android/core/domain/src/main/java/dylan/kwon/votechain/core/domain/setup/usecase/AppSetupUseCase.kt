package dylan.kwon.votechain.core.domain.setup.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.auth.port.AuthRepository
import dylan.kwon.votechain.core.domain.config.port.ConfigRepository
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import dylan.kwon.votechain.core.domain.setup.entity.AppSetupResult
import dylan.kwon.votechain.core.domain.vote.entity.toContractInfo
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import javax.inject.Inject

class AppSetupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val configRepository: ConfigRepository,
    private val cryptoWalletRepository: CryptoWalletRepository,
    private val voteRepository: VoteRepository,
) : UseCase<Unit, AppSetupResult>() {

    override suspend fun onInvoke(input: Unit): AppSetupResult {
        if (!checkAuth()) {
            return AppSetupResult.AuthError
        }
        if (!checkConfig()) {
            return AppSetupResult.ConfigError
        }
        if (!checkCryptoWallet()) {
            return AppSetupResult.NeedCryptoWallet
        }
        initVoteRepository()
        return AppSetupResult.Completed
    }

    private suspend fun checkAuth(): Boolean {
        return authRepository.auth()
    }

    private suspend fun checkConfig(): Boolean {
        if (configRepository.isReady()) {
            return true
        }
        return configRepository.setup()
    }

    private suspend fun checkCryptoWallet(): Boolean {
        return cryptoWalletRepository.hasCryptoWallet()
    }

    private suspend fun initVoteRepository() {
        voteRepository.updateContractInfo(
            info = configRepository.getConfig().toContractInfo()
        )
    }
}