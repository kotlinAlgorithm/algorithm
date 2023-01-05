/**
 * https://www.acmicpc.net/problem/5547
 * 백준 5547번 일루미네이션
 */
package week2

import java.util.StringTokenizer

lateinit var graph: Array<IntArray>
lateinit var visited: Array<BooleanArray>

var dRow = intArrayOf(-1, -1, 0, 0, 1, 1)
var dColumnOfOddRow = intArrayOf(-1, 0, -1, 1, -1, 0)
var dColumnOfEvenRow = intArrayOf(0, 1, -1, 1, 0, 1)

var sumOfWallCount = 0 // 벽면 길이

fun main() {
    var stringTokenizer = StringTokenizer(readln().trim())
    val width = stringTokenizer.nextToken().toInt()
    val height = stringTokenizer.nextToken().toInt()
    graph = Array(height) {
        stringTokenizer = StringTokenizer(readln().trim())
        IntArray(width) {
            stringTokenizer.nextToken().toInt()
        }
    }
    visited = Array(height) {
        BooleanArray(width)
    }

    findOuterSpace()
    calculateWallCount()
    print(sumOfWallCount)
}

/**
 * 바깥의 빈공간 찾기: 테두리에 있는 빈공간과 연결되어 있는 빈공간은 모두 바깥에 있는 빈공간이다
 */
fun findOuterSpace() {
    val width = graph[0].size
    val height = graph.size

    // 가로 테두리의 빈공간에서 dfs
    for (row in 0 until height) {
        if (row in 1 until height - 1) continue
        for (column in 0 until width) {
            if (graph[row][column] == 0 && visited[row][column].not()) {
                findOuterSpaceImpl(row, column)
            }
        }
    }

    // 세로 테두리의 빈공간에서 dfs
    for (column in 0 until width) {
        if (column in 1 until width - 1) continue
        for (row in 1 until height - 1) {
            if (graph[row][column] == 0 && visited[row][column].not()) {
                findOuterSpaceImpl(row, column)
            }
        }
    }
}

/**
 * 실제 바깥의 빈공간을 찾는 dfs 함수
 * graph에 마킹 - 바깥의 빈공간: 2, 안쪽의 밀폐된 빈공간: 0
 */
fun findOuterSpaceImpl(row: Int, column: Int) {
    visited[row][column] = true
    graph[row][column] = 2

    for (i in dRow.indices) {
        val nextRow = row + dRow[i]
        val nextColumn = column + if (row % 2 == 0) dColumnOfEvenRow[i] else dColumnOfOddRow[i]

        if (nextRow !in graph.indices || nextColumn !in graph[0].indices) continue
        if (graph[nextRow][nextColumn] == 0 && visited[nextRow][nextColumn].not()) {
            findOuterSpaceImpl(nextRow, nextColumn)
        }
    }
}

fun calculateWallCount() {
    for (row in graph.indices) {
        for (column in graph[0].indices) {
            if (graph[row][column] == 1 && visited[row][column].not()) {
                calculateWallCountImpl(row, column)
            }
        }
    }
}

/**
 * 건물을 대상으로 하는 dfs 함수
 * 건물과 인접한 바깥 빈공간 or 범위를 벗어나는 지점이 있으면 벽면 길이 증가
 */
fun calculateWallCountImpl(row: Int, column: Int) {
    visited[row][column] = true

    for (i in dRow.indices) {
        val nextRow = row + dRow[i]
        val nextColumn = column + if (row % 2 == 0) dColumnOfEvenRow[i] else dColumnOfOddRow[i]

        if (nextRow !in graph.indices || nextColumn !in graph[0].indices || graph[nextRow][nextColumn] == 2) {
            sumOfWallCount += 1
            continue
        }
        if (graph[nextRow][nextColumn] == 1 && visited[nextRow][nextColumn].not()) {
            calculateWallCountImpl(nextRow, nextColumn)
        }
    }
}