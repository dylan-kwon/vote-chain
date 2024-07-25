package dylan.kwon.votechain.core.driver.vote_contract.connector

import dylan.kwon.votechain.core.driver.vote_contract.VoteContractImpl
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.DefaultGasProvider

class Web3jVoteContractConnector(
    private val rpcUrl: String,
    private val contractAddress: String,
) : VoteContractConnector {

    override suspend fun <T> connection(
        privateKey: String,
        run: suspend (VoteContractImpl) -> T
    ): T? {
        var web3j: Web3j? = null
        val voteContract: VoteContractImpl
        try {
            web3j = createWeb3j()
            voteContract = createVoteContractImpl(web3j, privateKey)
            return run(voteContract)

        } finally {
            web3j?.shutdown()
        }
    }

    private fun createWeb3j(): Web3j {
        val webSocketService = createWebsocketService().apply {
            connect()
        }
        return Web3j.build(webSocketService)
    }

    private fun createWebsocketService(): WebSocketService {
        return WebSocketService(rpcUrl, true)
    }

    private fun createVoteContractImpl(web3j: Web3j, privateKey: String): VoteContractImpl {
        return VoteContractImpl.load(
            contractAddress,
            web3j,
            RawTransactionManager(web3j, Credentials.create(privateKey)),
            DefaultGasProvider()
        )
    }

}