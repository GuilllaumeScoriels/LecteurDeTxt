package UnusedFiles
//détient l’index courant (MutableStateFlow) + next(...) / previous() / setIndex(...).
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Contrôleur simple qui gère l'index courant.
 * Vous pouvez l'intégrer avec votre timer/TTS existants.
 */
class PlaybackController(
    initialIndex: Int = 0
) {
    private val _currentIndex = MutableStateFlow(initialIndex.coerceAtLeast(0))
    val currentIndex: StateFlow<Int> = _currentIndex

    fun setIndex(index: Int) {
        _currentIndex.value = index.coerceAtLeast(0)
    }

    fun next(stream: WordStream) {
        val next = (_currentIndex.value + 1).coerceAtMost(stream.size - 1)
        _currentIndex.value = next
    }

    fun previous() {
        val prev = (_currentIndex.value - 1).coerceAtLeast(0)
        _currentIndex.value = prev
    }
}
