package dylan.kwon.votechain.core.data.web3j.bip39

import dylan.kwon.votechain.core.data.web3j.bip39.model.Bip39CryptoWallet
import dylan.kwon.votechain.core.data.web3j.bip39.model.KeyPair

interface Bip39 {

    fun create(password: String): Bip39CryptoWallet

    fun loadKeyPair(password: String, cryptoWallet: Bip39CryptoWallet): KeyPair

}