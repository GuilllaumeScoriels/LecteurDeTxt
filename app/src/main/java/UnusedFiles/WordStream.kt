package UnusedFiles
//encapsule la liste de mots + méthodes wordAt(...), slice(...).
class WordStream(private val words: List<String>) {

    val size: Int get() = words.size

    fun wordAt(index: Int): String = words.getOrElse(index) { "" }

    fun slice(start: Int, endInclusive: Int): List<String> {
        if (words.isEmpty()) return emptyList()
        val s = start.coerceAtLeast(0)
        val e = endInclusive.coerceAtMost(words.lastIndex)
        if (s > e) return emptyList()
        return words.subList(s, e + 1)
    }
}
