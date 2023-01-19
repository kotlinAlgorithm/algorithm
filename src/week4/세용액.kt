import kotlin.math.absoluteValue
class `세용액`{
    fun main() {
        val N = readln().toInt()
        val arr = readln().split(" ").map { it.toLong() }.sorted()
        var minSum = (arr[0] + arr[1] + arr[2]).absoluteValue
        var minSumComb = listOf<Int>(0, 1, 2)

        var p1 = 0;
        var p2 = 0;
        var p3 = 0

        for (i in 0 until N - 2) {
            p3 = i
            p1 = p3 + 1
            p2 = arr.lastIndex

            while (p1 < p2) {
                var sum = arr[p1] + arr[p2] + arr[p3]

                if (sum.absoluteValue < minSum) {
                    minSum = sum.absoluteValue
                    minSumComb = listOf<Int>(p1, p2, p3)
                }

                if (sum < 0) {
                    p1++
                } else if (sum == 0L) {
                    break
                } else {
                    p2--
                }
            }

        }


        println(minSumComb.map { arr[it] }.sorted().joinToString(" "))

    }
}
