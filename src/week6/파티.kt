package week6

/**
 * https://www.acmicpc.net/problem/1238
 * 다익스트라 응용
 */
import java.util.PriorityQueue

data class Edge(val toTown: Int, val time: Int)
data class Route(val currentTown: Int, val currentAccTime: Int)

fun main() {
    val (nodeCount, edgeCount, dstTown) = readln().trim().split(" ").map(String::toInt)
    val linkedTowns = Array(nodeCount + 1) { mutableListOf<Edge>() }
    val reversedLinkedTowns = Array(nodeCount + 1) { mutableListOf<Edge>() }

    repeat(edgeCount) {
        val (fromTown, toTown, time) = readln().trim().split(" ").map(String::toInt)
        linkedTowns[fromTown].add(Edge(toTown, time))
        reversedLinkedTowns[toTown].add(Edge(fromTown, time))
    }

    val minTimeFromDst: IntArray = dijkstra(linkedTowns, dstTown)
    val reversedMinTimeFromDst: IntArray = reverseDijkstra(reversedLinkedTowns, dstTown)

    var maxTotalTime =  Int.MIN_VALUE
    for (i in 1..nodeCount) {
        maxTotalTime = maxOf(maxTotalTime, minTimeFromDst[i] + reversedMinTimeFromDst[i])
    }
    print(maxTotalTime)
}

fun dijkstra(linkedTowns: Array<MutableList<Edge>>, dstTown: Int): IntArray {
    val minTimeFromDst = IntArray(linkedTowns.size) { Int.MAX_VALUE }
    val pq = PriorityQueue<Route>(compareBy { it.currentAccTime })
    pq.offer(Route(dstTown, 0))
    minTimeFromDst[dstTown] = 0

    while(pq.isNotEmpty()) {
        val (currentTown, currentAccTime) = pq.poll()
        if (currentAccTime > minTimeFromDst[currentTown]) {
            continue
        }

        for (adjacentEdge in linkedTowns[currentTown]) {
            val newAccTime = currentAccTime + adjacentEdge.time
            if (newAccTime < minTimeFromDst[adjacentEdge.toTown]) {
                pq.offer(Route(adjacentEdge.toTown, newAccTime))
                minTimeFromDst[adjacentEdge.toTown] = newAccTime
            }
        }
    }

    return minTimeFromDst
}

fun reverseDijkstra(reversedLinkedTowns: Array<MutableList<Edge>>, dstTown: Int): IntArray {
    val reversedMinTimeFromDst = IntArray(reversedLinkedTowns.size) { Int.MAX_VALUE }
    val pq = PriorityQueue<Route>(compareBy { it.currentAccTime })
    pq.offer(Route(dstTown, 0))
    reversedMinTimeFromDst[dstTown] = 0

    while(pq.isNotEmpty()) {
        val (currentTown, currentAccTime) = pq.poll()
        if (currentAccTime > reversedMinTimeFromDst[currentTown]) {
            continue
        }

        for (adjacentEdge in reversedLinkedTowns[currentTown]) {
            val newAccTime = currentAccTime + adjacentEdge.time
            if (newAccTime < reversedMinTimeFromDst[adjacentEdge.toTown]) {
                pq.offer(Route(adjacentEdge.toTown, newAccTime))
                reversedMinTimeFromDst[adjacentEdge.toTown] = newAccTime
            }
        }
    }

    return reversedMinTimeFromDst
}