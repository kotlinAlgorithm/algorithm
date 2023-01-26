package week5

fun main() {
    val LEFT = 0
    val RIGHT = 1
    val NONE = 2
    val MOD = 9901

    val maxDepth = readln().trim().toInt()
    val dpTable = Array(maxDepth + 1) {
        IntArray(3)
    }
    dpTable[1][LEFT] = 1
    dpTable[1][RIGHT] = 1
    dpTable[1][NONE] = 1

    for (depth in 2..maxDepth) {
        dpTable[depth][LEFT] = (dpTable[depth - 1][RIGHT] + dpTable[depth - 1][NONE]) % MOD
        dpTable[depth][RIGHT] = dpTable[depth - 1][LEFT] + dpTable[depth - 1][NONE] % MOD
        dpTable[depth][NONE] = (dpTable[depth - 1][LEFT] + dpTable[depth - 1][RIGHT] + dpTable[depth - 1][NONE]) % MOD
    }

    print(dpTable[maxDepth].sum() % MOD)
}