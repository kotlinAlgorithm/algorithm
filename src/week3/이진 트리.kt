package week3

fun main() {
    val treeHeight = readln().trim().toInt()
    val edgeWeights = listOf(0) + readln().trim().split(" ").map(String::toInt)
    val routeToLeafSum = IntArray(1.shl(treeHeight + 1))

    for (i in 1.shl(treeHeight) - 1 downTo 1) {
        routeToLeafSum[i] = maxOf(
            edgeWeights[2 * i - 1] + routeToLeafSum[2 * i],
            edgeWeights[2 * i] + routeToLeafSum[2 * i + 1]
        )
    }

    var edgeSum = 0
    for (i in 1 until 1.shl(treeHeight)) {
        edgeSum += 2 * routeToLeafSum[i] - routeToLeafSum[2 * i] - routeToLeafSum[2 * i + 1]
    }

    print(edgeSum)
}