package dylan.kwon.votechain.feature.auth.ui.composable.numPad

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun rememberNumPadState(
    isShuffle: Boolean = true
): NumPadState = remember(isShuffle) {
    NumPadState(isShuffle)
}

@Stable
@SuppressLint("MutableCollectionMutableState")
class NumPadState(
    val isShuffle: Boolean
) {

    sealed interface Data {
        data class Number(
            val value: Int
        ) : Data

        data object Delete : Data

        data object Empty : Data
    }

    private val _data by lazy {
        mutableListOf<Data>().apply {
            // Add Num: 0 ~ 9
            repeat(10) {
                this += Data.Number(value = it)
            }

            // Add Blank
            this += Data.Empty

            // Shuffle
            if (isShuffle) {
                shuffle()
            }

            // Add Delete
            this += Data.Delete
        }.let {
            mutableStateOf(it.toImmutableList())
        }
    }
    val data: State<ImmutableList<Data>> = _data
}