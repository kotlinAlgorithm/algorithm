package week4

/***
 * https://www.acmicpc.net/problem/16946
 */
lateinit var graph: Array<IntArray>
lateinit var groupNumberGraph: Array<IntArray>
lateinit var populations: IntArray
var groupNumber = 1
val dRow = intArrayOf(-1, 1, 0 ,0)
val dCol = intArrayOf(0, 0, -1, 1)

fun main() {
    val (rowCount, colCount) = readln().trim().split(" ").map(String::toInt)
    graph = Array(rowCount) {
        readln().trim().map { it - '0' }.toIntArray()
    }
    groupNumberGraph = Array(rowCount) {
        IntArray(colCount)
    }
    populations = IntArray(rowCount * colCount + 1)

    for (row in graph.indices) {
        for (col in graph[0].indices) {
            if (graph[row][col] == 0 && groupNumberGraph[row][col] == 0) {
                groupLinkedZero(row, col, groupNumber)
                groupNumber++
            }
        }
    }

    print(buildResultGraph())
}

fun groupLinkedZero(row: Int, col: Int, groupNumber: Int) {
    groupNumberGraph[row][col] = groupNumber
    populations[groupNumber]++

    for (i in 0 until 4) {
        val newRow = row + dRow[i]
        val newCol = col + dCol[i]
        if (newRow !in graph.indices || newCol !in graph[0].indices) continue
        if (graph[newRow][newCol] == 1 || groupNumberGraph[newRow][newCol] > 0) continue
        groupLinkedZero(newRow, newCol, groupNumber)
    }
}

fun buildResultGraph(): String {
    val sb = StringBuilder()
    val groupNumberSet = HashSet<Int>()

    for (row in graph.indices) {
        for (col in graph[0].indices) {
            if (graph[row][col] == 0) {
                sb.append(0)
            } else {
                var reachableBlockCount = 1

                for (i in 0 until 4) {
                    val newRow = row + dRow[i]
                    val newCol = col + dCol[i]
                    if (newRow !in graph.indices || newCol !in graph[0].indices || graph[newRow][newCol] == 1) continue

                    val groupNumber = groupNumberGraph[newRow][newCol]
                    if (groupNumberSet.contains(groupNumber).not()){
                        reachableBlockCount += populations[groupNumber]
                        groupNumberSet.add(groupNumber)
                    }
                }

                sb.append(reachableBlockCount % 10)
                groupNumberSet.clear()
            }
        }
        sb.appendLine()
    }

    return sb.toString()
}