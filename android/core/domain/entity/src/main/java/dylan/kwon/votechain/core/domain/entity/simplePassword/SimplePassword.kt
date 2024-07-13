package dylan.kwon.votechain.core.domain.entity.simplePassword

data class SimplePassword(
    val value: String
) {

    companion object {
        const val MAXIMUM_PASSWORD_SIZE = 6
    }

    val isValidate = value.length == MAXIMUM_PASSWORD_SIZE
}