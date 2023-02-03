package week7

/**
 * https://www.acmicpc.net/problem/2252
 */
import java.util.PriorityQueue

data class Edge(val house1: Int, val house2: Int, val cost: Int)

lateinit var groupLeaders: IntArray

fun main() {
    val (houseCount, edgeCount) = readln().split(" ").map(String::toInt)
    val pq = PriorityQueue<Edge>(compareBy { it.cost })
    groupLeaders = IntArray(houseCount + 1) { i -> i }

    repeat(edgeCount) {
        val (house1, house2, cost) = readln().trim().split(" ").map(String::toInt)
        pq.offer(Edge(house1, house2, cost))
    }

    val minCostSum = findMinCostSum(pq, houseCount)
    print(minCostSum)
}

fun findLeader(house:Int): Int {
    if (groupLeaders[house] == house) return house
    return findLeader(groupLeaders[house]).also { leader ->
        groupLeaders[house] = leader
    }
}

/**
 * 최소 스패닝 트리 알고리즘
 */
fun findMinCostSum(pq: PriorityQueue<Edge>, houseCount: Int): Int {
    var groupCount = houseCount
    var costSum = 0

    // group 개수가 2개로 줄어 들었으면 종료
    while (groupCount > 2) {
        val (house1, house2, cost) = pq.poll()
        val leader1 = findLeader(house1)
        val leader2 = findLeader(house2)

        if (leader1 != leader2) {
            groupLeaders[leader2] = leader1
            groupCount -= 1
            costSum += cost
        }
    }

    return costSum
}