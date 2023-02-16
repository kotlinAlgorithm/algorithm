package week8

data class Paint(val length: Int, val price: Int, var withSellablePaint: Int)

fun main() {
    val (paintCount, sellLength) = readln().split(" ").map(String::toInt)
    val paints = arrayOf(Paint(0, 0, 0)) +
            Array(paintCount) {
                readln().split(" ").map(String::toInt).let { (length, price) ->
                    Paint(length, price, 0)
                }
            } + arrayOf(Paint(20_000_001, 0, paintCount))
    val dp = IntArray(paintCount + 1)

    paints.sortBy { it.length }
    for (i in paintCount downTo 2) {
        for (j in paints[i + 1].withSellablePaint downTo 1) {
            if (paints[i].length - paints[j].length >= sellLength) {
                paints[i].withSellablePaint = j
                break
            }
        }
    }

    for (i in 1 .. paintCount) {
        dp[i] = maxOf(dp[i - 1], paints[i].price + dp[paints[i].withSellablePaint])
    }
    print(dp[paintCount])
}