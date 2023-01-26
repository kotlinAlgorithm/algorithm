package week5

const val PASSWORD_MAX_LENGTH = 1000
const val MAX_NUMBER = 9
const val MOD = 1_234_567L
val adjacentNumbers: Array<IntArray> = arrayOf(
    intArrayOf(7),
    intArrayOf(2, 4), intArrayOf(1, 3, 5), intArrayOf(2, 6),
    intArrayOf(1, 5, 7), intArrayOf(2, 4, 6, 8), intArrayOf(3, 5, 9),
    intArrayOf(0, 4, 8), intArrayOf(5, 7, 9), intArrayOf(6, 8),
)
lateinit var dpTable: Array<LongArray>

fun main() {
    initDpTable()
    processSolution()
}

fun initDpTable() {
    dpTable = Array(PASSWORD_MAX_LENGTH + 1) { LongArray(MAX_NUMBER + 1) }
    for (number in 0..MAX_NUMBER) {
        dpTable[1][number] = 1
    }

    for (length in 2..PASSWORD_MAX_LENGTH){
        for (number in 0..MAX_NUMBER) {
            for (adjacentNumber in adjacentNumbers[number]) {
                dpTable[length][number] += dpTable[length - 1][adjacentNumber]
                dpTable[length][number] %= MOD
            }
        }
    }
}

fun processSolution() {
    val answerStringBuilder = StringBuilder()

    repeat(readln().trim().toInt()) {
        readln().trim().toInt().also { passwordLength ->
            answerStringBuilder.append("${dpTable[passwordLength].reduce { sum, pwdCount -> (sum + pwdCount) % MOD}}\n")
        }
    }

    print(answerStringBuilder.toString())
}