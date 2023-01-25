import java.io.BufferedReader
import java.io.InputStreamReader

val br = BufferedReader(InputStreamReader(System.`in`))

class `acw수문` {
    fun solution() {
        val n = readln().trim().toInt()
        val dam = arrayListOf<Pair<Long, Long>>()

        repeat(n) {
            val (power, cost) = br.readLine().trim().split(" ").map { it.toLong() }
            dam.add(Pair(power, cost))
        }


        val m = br.readLine().toInt()
        for (i in 1..m) {
            var (targetQuantity, targetHour) = br.readLine().trim().split(" ").map { it.toLong() }
            var minCost = Long.MAX_VALUE
            var answerString = "Case $i: "

            for (combSize in 1..n) {
                val allComb = mutableListOf<List<Pair<Long, Long>>>()
                comb(allComb, dam, Array(dam.size) { false }, combSize, 0)
                allComb.forEach { case ->
                    val powerNow = case.sumOf { it.first } * targetHour
                    val costNow = case.sumOf { it.second }
                    if (powerNow > targetQuantity && costNow < minCost) {
                        minCost = costNow
                    }
                }
            }


            if (minCost == Long.MAX_VALUE) {
                answerString += "IMPOSSIBLE"
            } else {
                answerString += minCost
            }

            println(answerString)
        }
    }

    fun comb(
        allComb: MutableList<List<Pair<Long, Long>>>,
        dams: List<Pair<Long, Long>>,
        visit: Array<Boolean>,
        targetNum: Int,
        start: Int
    ) {
        if (targetNum == 0) {
            allComb.add(dams.filterIndexed { index, _ -> visit[index] })
        } else {
            for (i in start until visit.size) {
                visit[i] = true
                comb(allComb, dams, visit, targetNum - 1, i + 1)
                visit[i] = false
            }
        }
    }
}

fun main() {
    val sol = `acw수문`()
    sol.solution()
}
