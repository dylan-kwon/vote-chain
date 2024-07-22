package dylan.kwon.votechain.core.data.web3j.bip39.model

class Credential(
    val public: ByteArray,
    val private: ByteArray,
    val address: String
)