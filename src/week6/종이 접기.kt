package week6

/**
 * https://www.acmicpc.net/problem/1802
 * 이해 못함
 */

lateinit var pattern: String

fun main() {
    val stringBuilder = StringBuilder()

    repeat(readln().trim().toInt()) {
        pattern = readln()
        if (areMarkReversed(0, pattern.lastIndex)) {
            stringBuilder.append("YES")
        } else {
            stringBuilder.append("NO")
        }
        stringBuilder.appendLine()
    }

    print(stringBuilder.toString())
}

private fun areMarkReversed(startIndex: Int, endIndex: Int): Boolean {
    if (endIndex == startIndex) {
        return true
    }

    val middleIndex = (startIndex + endIndex) / 2
    for (i in 0 until middleIndex - startIndex) {
        if (pattern[startIndex + i] == pattern[endIndex - i]){
            return false
        }
    }

    return areMarkReversed(startIndex, middleIndex - 1)
}

