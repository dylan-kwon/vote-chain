package dylan.kwon.votechain.core.domain.setup.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.auth.port.AuthPort
import dylan.kwon.votechain.core.domain.config.port.ConfigPort
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import dylan.kwon.votechain.core.domain.setup.entity.AppSetupResult
import dylan.kwon.votechain.core.domain.vote.entity.toContractInfo
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import javax.inject.Inject

class AppSetupUseCase @Inject constructor(
    private val authPort: AuthPort,
    private val configPort: ConfigPort,
    private val cryptoWalletPort: CryptoWalletPort,
    private val votePort: VotePort,
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
        initVotePort()
        return AppSetupResult.Completed
    }

    private suspend fun checkAuth(): Boolean {
        return authPort.auth()
    }

    private suspend fun checkConfig(): Boolean {
        if (configPort.isReady()) {
            return true
        }
        return configPort.setup()
    }

    private suspend fun checkCryptoWallet(): Boolean {
        return cryptoWalletPort.hasCryptoWallet()
    }

    private suspend fun initVotePort() {
        votePort.updateContractInfo(
            info = configPort.getConfig().toContractInfo()
        )
    }
}