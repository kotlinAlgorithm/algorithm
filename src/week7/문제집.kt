package week7

import java.util.PriorityQueue

fun main() {
    val (problemCount, linkCount) = readln().split(" ").map(String::toInt)
    val nextProblems = Array(problemCount + 1) { mutableListOf<Int>() }
    val prevProblemCounts = IntArray(problemCount + 1)

    repeat(linkCount) {
        val (prevProblem, nextProblem) = readln().split(" ").map(String::toInt)
        nextProblems[prevProblem].add(nextProblem)
        prevProblemCounts[nextProblem] += 1
    }

    val sortedResult = sort(nextProblems, prevProblemCounts)
    print(sortedResult)
}

private fun sort(nextProblems: Array<MutableList<Int>>, prevProblemCounts: IntArray): String {
    val pq = PriorityQueue<Int>()
    for (problem in 1..prevProblemCounts.lastIndex) {
        if (prevProblemCounts[problem] == 0) {
            pq.offer(problem)
        }
    }

    val sb = StringBuilder()
    while (pq.isNotEmpty()) {
        val problem = pq.poll()
        sb.append("$problem ")

        nextProblems[problem].forEach { nextProblem ->
            prevProblemCounts[nextProblem] -= 1
            if (prevProblemCounts[nextProblem] == 0) {
                pq.offer(nextProblem)
            }
        }
    }

    return sb.toString()
}