package week7

/**
 * https://www.acmicpc.net/problem/2252
 */
import java.util.Queue
import java.util.LinkedList

fun main() {
    val (studentCount, comparisonCount) = readln().split(" ").map(String::toInt)
    val adjacentStudents = Array(studentCount + 1) { mutableListOf<Int>() }
    val smallerStudentsCount = IntArray(studentCount + 1)

    repeat(comparisonCount) {
        val (small, tall) = readln().split(" ").map(String::toInt)
        adjacentStudents[small].add(tall)
        smallerStudentsCount[tall] += 1
    }

    val sortedResult = sorted(adjacentStudents,smallerStudentsCount)
    print(sortedResult)
}

private fun sorted(adjacentStudents: Array<MutableList<Int>>, smallerStudentsCount: IntArray): String {
    val sb = StringBuilder()
    val queue: Queue<Int> = LinkedList()

    for (student in 1 until adjacentStudents.size) {
        if (smallerStudentsCount[student] == 0) {
            queue.offer(student)
        }
    }

    while (queue.isNotEmpty()) {
        val student = queue.poll()
        sb.append("$student ")

        adjacentStudents[student].forEach { nextStudent ->
            smallerStudentsCount[nextStudent] -= 1
            if (smallerStudentsCount[nextStudent] <= 0) {
                queue.offer(nextStudent)
            }
        }
    }

    return sb.toString()
}