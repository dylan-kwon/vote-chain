package dylan.kwon.votechain.core.ui.design_system.theme.composable.messageCard

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    message: String,
    state: MessageCard.State
) {
    AnimatedContent(
        modifier = modifier,
        targetState = message,
        label = "messageCard-crossFade"
    ) {
        when (state) {
            MessageCard.State.Default -> DefaultMessageCard(message = message)
            MessageCard.State.Success -> SuccessMessageCard(message = message)
            MessageCard.State.Error -> ErrorMessageCard(message = message)
        }
    }
}


@Composable
private fun DefaultMessageCard(message: String) {
    Card {
        MessageText(
            text = message
        )
    }
}

@Composable
private fun SuccessMessageCard(message: String) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        MessageText(
            text = message
        )
    }
}

@Composable
private fun ErrorMessageCard(message: String) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        MessageText(
            text = message
        )
    }
}

@Composable
private fun MessageText(text: String) {
    Text(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp,
            ),
        text = text,
        style = MaterialTheme.typography.headlineSmall
    )
}