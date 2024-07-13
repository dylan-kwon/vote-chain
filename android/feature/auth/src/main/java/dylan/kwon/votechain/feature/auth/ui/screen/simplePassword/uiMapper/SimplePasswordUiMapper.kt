package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.uiMapper

import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword

interface SimplePasswordUiMapper {
    fun List<String>.toSimplePassword(): SimplePassword
}