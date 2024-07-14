package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.uiMapper

import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword

interface SimplePasswordUiMapper {
    fun List<String>.toSimplePassword(): SimplePassword
}