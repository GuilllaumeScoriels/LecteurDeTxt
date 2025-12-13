package reader.core
//calcule les indices visibles autour de l’index courant
data class WindowIndices(val start: Int, val end: Int)

class WindowStrategy private constructor(
    private val left: Int,
    private val right: Int
) {
    fun windowAround(currentIndex: Int, total: Int): WindowIndices {
        val start = (currentIndex - left).coerceAtLeast(0)
        val end = (currentIndex + right).coerceAtMost(total - 1)
        return WindowIndices(start, end)
    }

    companion object {
        fun surrounding(left: Int, right: Int) = WindowStrategy(left, right)
    }
}
