package dylan.kwon.votechain.core.data.web3j.bip39

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dylan.kwon.votechain.core.data.web3j.bip39.model.Bip39CryptoWallet
import dylan.kwon.votechain.core.data.web3j.bip39.model.KeyPair
import org.web3j.crypto.WalletUtils
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Web3jBip39 @Inject constructor(

    @ApplicationContext
    private val context: Context

) : Bip39 {

    private val cryptoWalletDir by lazy {
        File(context.filesDir, "bip-39").apply {
            mkdirs()
        }
    }

    override fun create(password: String): Bip39CryptoWallet {
        val newWallet = WalletUtils.generateBip39Wallet(
            password, cryptoWalletDir
        )
        return Bip39CryptoWallet(
            mnemonic = newWallet.mnemonic.split(" ")
        )
    }

    override fun loadKeyPair(password: String, cryptoWallet: Bip39CryptoWallet): KeyPair {
        val credentials = WalletUtils.loadBip39Credentials(
            password,
            cryptoWallet.mnemonic.joinToString(separator = " ")
        )
        return KeyPair(
            public = credentials.ecKeyPair.publicKey.toByteArray(),
            private = credentials.ecKeyPair.privateKey.toByteArray()
        )
    }
}