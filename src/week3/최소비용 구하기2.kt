package week3

import java.util.PriorityQueue
import java.util.LinkedList

data class Edge(val toCity: Int, val cost: Int)
data class Route(val cityCount: Int, val cityList: List<Int>)

lateinit var graph: Array<MutableList<Edge>>
lateinit var minCostFromStart: IntArray
lateinit var prevCity: IntArray

fun main() {
    val citySize = readln().trim().toInt()
    val busSize = readln().trim().toInt()

    graph = Array(citySize + 1) {
        mutableListOf<Edge>()
    }.apply {
        repeat(busSize) {
            val (fromCity, toCity, cost) = readln().trim().split(" ").map(String::toInt)
            this[fromCity].add(Edge(toCity, cost))
        }
    }
    minCostFromStart = IntArray(citySize + 1) { Int.MAX_VALUE }
    prevCity = IntArray(citySize + 1)

    val (fromCity, toCity) = readln().trim().split(" ").map(String::toInt)
    executeDijkstra(fromCity)
    val route = getMinCostRouteFromStart(toCity)
    println(minCostFromStart[toCity])
    println(route.cityCount)
    print(route.cityList.joinToString(" "))
}

fun executeDijkstra(fromCity: Int) {
    val pq = PriorityQueue<Edge>(compareBy { it.cost })
    pq.offer(Edge(fromCity, 0))
    minCostFromStart[fromCity] = 0
    prevCity[fromCity] = 0

    while(pq.isNotEmpty()) {
        val middleEdge = pq.poll()
        if (middleEdge.cost > minCostFromStart[middleEdge.toCity]) {
            continue
        }

        graph[middleEdge.toCity].forEach { newEdge ->
            val newCost = middleEdge.cost + newEdge.cost
            if (newCost < minCostFromStart[newEdge.toCity]) {
                minCostFromStart[newEdge.toCity] = newCost
                prevCity[newEdge.toCity] = middleEdge.toCity
                pq.offer(Edge(newEdge.toCity, newCost))
            }
        }
    }
}

fun getMinCostRouteFromStart(toCity: Int): Route {
    var cityCount = 0
    val cityList = LinkedList<Int>()
    var middleCity = toCity

    while (middleCity != 0) {
        cityList.addFirst(middleCity)
        cityCount += 1
        middleCity = prevCity[middleCity]
    }

    return Route(cityCount, cityList)
}