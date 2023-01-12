package week3.acw

import java.util.PriorityQueue

const val MAX = 10000001

class 최소비용구하기2 {


    lateinit var graph: Array<MutableList<Pair<Int, Int>>>
    lateinit var distance: Array<Int>
    lateinit var minCity: Array<MutableSet<Int>>
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

    fun dijkstra(start: Int) {
        pq.add(Pair(0, start))
        distance[start] = 0

        while (pq.isNotEmpty()) {
            val (dist, now) = pq.poll()
            if (distance[now] < dist) {
                continue
            }
            for (i in graph[now]) {
                val cost = dist + i.second
                if (cost < distance[i.first]) {
                    distance[i.first] = cost
                    pq.add(Pair(cost, i.first))
                    minCity[i.first].addAll(minCity[now])
                    minCity[i.first].remove(i.first)
                    minCity[i.first].add(i.first)
                }
            }
        }

    }

    fun main() {
        val n = readln().toInt()
        val m = readln().toInt()
        distance = Array(n + 1) { MAX }
        graph = Array(n + 1) { mutableListOf() }
        minCity = Array(n + 1) { mutableSetOf(it) }

        repeat(m) {
            val (a, b, c) = readln().split(" ").map { it.toInt() }
            graph[a].add(Pair(b, c))
        }
        val (start, end) = readln().split(" ").map { it.toInt() }


        dijkstra(start)

        println(distance[end])
        println(minCity[end].size)
        println(minCity[end].joinToString(" "))

    }
}