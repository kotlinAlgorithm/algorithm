package week7

import java.util.PriorityQueue

data class Jewel(val weight: Int, val price: Int)

fun main() {
    val (jewelCount, bagCount) = readln().split(" ").map(String::toInt)
    val jewels = Array(jewelCount) {
        readln().split(" ").map(String::toInt).let { jewelInfo ->
            Jewel(jewelInfo[0], jewelInfo[1])
        }
    }
    val bags = IntArray(bagCount) { readln().toInt() }
    print(getMaxPriceSum(jewels, bags))
}

private fun getMaxPriceSum(jewels: Array<Jewel>, bags: IntArray): Long {
    var result = 0L
    var jewelIndex = 0
    val pq = PriorityQueue<Jewel>(compareByDescending { it.price })

    jewels.sortBy{ it.weight }
    bags.sort()

    bags.forEach { bag ->
        // 매가방마다 무게를 만족하는 모든 보석을 우선순위 큐에 넣는다
        while (jewelIndex < jewels.size && jewels[jewelIndex].weight <= bag) {
            pq.offer(jewels[jewelIndex])
            jewelIndex += 1
        }

        // 루트에 가방의 무게를 만족하는 가장 비싼 보석이 있다
        if (pq.isNotEmpty()) {
            result += pq.poll().price
        }
    }

    return result
}