package dylan.kwon.votechain.core.domain.auth.entity

data class SimplePassword(
    val value: String
) {

    companion object {
        const val PASSWORD_LENGTH = 6
    }

    internal val isValidate = value.length == PASSWORD_LENGTH
}