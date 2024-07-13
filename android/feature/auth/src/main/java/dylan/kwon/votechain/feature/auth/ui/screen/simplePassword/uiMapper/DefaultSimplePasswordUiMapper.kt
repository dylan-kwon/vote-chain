package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.uiMapper

import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSimplePasswordUiMapper @Inject constructor() : SimplePasswordUiMapper {
    override fun List<String>.toSimplePassword(): SimplePassword = SimplePassword(
        value = joinToString(separator = "")
    )
}