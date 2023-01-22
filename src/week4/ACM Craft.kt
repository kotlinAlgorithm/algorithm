package week4

/**
 * https://www.acmicpc.net/problem/1005
 */
import java.util.LinkedList

data class Route(val currentBuilding: Int, val totalBuiltTime: Int)

fun main() {
    repeat(readln().trim().toInt()) {
        val (buildingSize, edgeSize) = readln().split(" ").map(String::toInt)
        val buildTime: List<Int> = listOf(0) + readln().split(" ").map(String::toInt)
        val reversedLinkedBuildings = Array(buildingSize + 1) { mutableListOf<Int>() }.also { it[0].add(-1) }

        // 그래프 방향 뒤집기
        repeat(edgeSize) {
            readln().split(" ").run {
                reversedLinkedBuildings[this[1].toInt()].add(this[0].toInt())
            }
        }

        val finalBuilding = readln().toInt()
        val maxBuiltTime = IntArray(buildingSize + 1) { -1 }
        val q = LinkedList<Route>()

        q.offer(Route(finalBuilding, buildTime[finalBuilding]))
        maxBuiltTime[finalBuilding] = buildTime[finalBuilding]
        while (q.isNotEmpty()) {
            val route = q.poll()

            if (route.totalBuiltTime < maxBuiltTime[route.currentBuilding]) {
                continue
            }

            for (nextBuilding in reversedLinkedBuildings[route.currentBuilding]) {
                val nextTotalBuiltTime = route.totalBuiltTime + buildTime[nextBuilding]
                if (nextTotalBuiltTime > maxBuiltTime[nextBuilding]) {
                    q.offer(Route(nextBuilding, nextTotalBuiltTime))
                    maxBuiltTime[nextBuilding] = nextTotalBuiltTime
                }
            }
        }

        println(
            maxBuiltTime.filterIndexed { i, _ -> reversedLinkedBuildings[i].isEmpty() }.max()
        )
    }
}