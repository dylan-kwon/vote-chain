package dylan.kwon.votechain.core.data.web3j.bip39

import dylan.kwon.votechain.core.data.web3j.bip39.model.Bip39CryptoWallet
import dylan.kwon.votechain.core.data.web3j.bip39.model.Credential

interface Bip39 {

    fun create(password: String): Bip39CryptoWallet

    fun loadCredentials(password: String, cryptoWallet: Bip39CryptoWallet): Credential

}