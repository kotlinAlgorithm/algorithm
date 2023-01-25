import java.util.PriorityQueue

/*
greedy로 최대한 앞에 있는 집들 부터 먼저 증가시키면서 색칠하기
 */
class `acw구름다리2` {
    fun solution() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val building = Array<Int>(n + 1) { 1 }
        val bridges = PriorityQueue(Comparator<Pair<Int, Int>> { a, b ->
            if (b.second == a.second) a.first - b.first
            else a.second - b.second
        })

        for (i in 0 until m) {
            var (a, b) = readln().split(" ").map { it.toInt() }
            if (a > b) {
                val tmp = b
                b = a
                a = tmp
            }
            bridges.add(Pair(a, b))
        }

        while (bridges.isNotEmpty()) {
            val bridgeNow = bridges.poll()
            if (building[bridgeNow.second] != building[bridgeNow.first]) continue

            building[bridgeNow.second] = building[bridgeNow.first] + 1
        }

        println(building.drop(1).joinToString(" "))


    }
}

fun main() {
    val sol = `acw구름다리2`()
    sol.solution()
}
