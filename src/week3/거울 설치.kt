package week3

/**
 * https://www.acmicpc.net/problem/2151
 */

import java.util.PriorityQueue

data class State(val row: Int, val col: Int, val dir: Int, val mirrorCount: Int)

fun main() {
    val houseSize = readln().trim().toInt()
    val house = Array<CharArray>(houseSize) { readln().toCharArray() }
    val mirrorCounts = Array(houseSize) {
        Array(houseSize) {
            IntArray(4) {
                Int.MAX_VALUE
            }
        }
    }
    val pq = PriorityQueue<State>(compareBy { it.mirrorCount })
    val dRow = intArrayOf(-1, 1, 0, 0) // 상하좌우
    val dCol = intArrayOf(0, 0, -1, 1)
    var startRow = 0
    var startCol = 0

    for (row in house.indices) {
        for (col in house[0].indices) {
            if (house[row][col] == '#' && pq.isEmpty()) {
                startRow = row
                startCol = col
                for (dir in 0 until 4) {
                    pq.offer(State(startRow, startCol,  dir, 0))
                    mirrorCounts[startRow][startCol][dir] = 0
                }
            }
        }
    }
    while(pq.isNotEmpty()) {
        val (row, col, dir, currentMirrorCount) = pq.poll()
        if (currentMirrorCount > mirrorCounts[row][col][dir]) {
            continue
        }
        if (house[row][col] == '#' && (row != startRow || col != startCol)) {
            print(currentMirrorCount)
            return
        }

        val nextRow = row + dRow[dir]
        val nextCol = col + dCol[dir]
        if (nextRow in house.indices && nextCol in house[0].indices && house[nextRow][nextCol] != '*') {
            if (currentMirrorCount < mirrorCounts[nextRow][nextCol][dir]) {
                pq.offer(State(nextRow, nextCol, dir, currentMirrorCount))
                mirrorCounts[nextRow][nextCol][dir] = currentMirrorCount
            }
        }

        if (house[row][col] == '!') {
            when(dir) {
                0 -> {
                    val nextRow1 = row + dRow[3]
                    val nextCol1 = col + dCol[3]
                    if (nextRow1 in house.indices && nextCol1 in house[0].indices && house[nextRow1][nextCol1] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow1][nextCol1][3]) {
                            pq.offer(State(nextRow1, nextCol1, 3, currentMirrorCount + 1))
                            mirrorCounts[nextRow1][nextCol1][3] = currentMirrorCount + 1
                        }
                    }

                    val nextRow2 = row + dRow[2]
                    val nextCol2 = col + dCol[2]
                    if (nextRow2 in house.indices && nextCol2 in house[0].indices && house[nextRow2][nextCol2] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow2][nextCol2][2]) {
                            pq.offer(State(nextRow2, nextCol2, 2, currentMirrorCount + 1))
                            mirrorCounts[nextRow2][nextCol2][2] = currentMirrorCount + 1
                        }
                    }
                }
                1 -> {
                    val nextRow1 = row + dRow[2]
                    val nextCol1 = col + dCol[2]
                    if (nextRow1 in house.indices && nextCol1 in house[0].indices && house[nextRow1][nextCol1] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow1][nextCol1][2]) {
                            pq.offer(State(nextRow1, nextCol1, 2, currentMirrorCount + 1))
                            mirrorCounts[nextRow1][nextCol1][2] = currentMirrorCount + 1
                        }
                    }

                    val nextRow2 = row + dRow[3]
                    val nextCol2 = col + dCol[3]
                    if (nextRow2 in house.indices && nextCol2 in house[0].indices && house[nextRow2][nextCol2] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow2][nextCol2][3]) {
                            pq.offer(State(nextRow2, nextCol2, 3, currentMirrorCount + 1))
                            mirrorCounts[nextRow2][nextCol2][3] = currentMirrorCount + 1
                        }
                    }
                }
                2 -> {
                    val nextRow1 = row + dRow[0]
                    val nextCol1 = col + dCol[0]
                    if (nextRow1 in house.indices && nextCol1 in house[0].indices && house[nextRow1][nextCol1] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow1][nextCol1][0]) {
                            pq.offer(State(nextRow1, nextCol1, 0, currentMirrorCount + 1))
                            mirrorCounts[nextRow1][nextCol1][0] = currentMirrorCount + 1
                        }
                    }

                    val nextRow2 = row + dRow[1]
                    val nextCol2 = col + dCol[1]
                    if (nextRow2 in house.indices && nextCol2 in house[0].indices && house[nextRow2][nextCol2] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow2][nextCol2][1]) {
                            pq.offer(State(nextRow2, nextCol2, 1, currentMirrorCount + 1))
                            mirrorCounts[nextRow2][nextCol2][1] = currentMirrorCount + 1
                        }
                    }
                }
                else -> {
                    val nextRow1 = row + dRow[1]
                    val nextCol1 = col + dCol[1]
                    if (nextRow1 in house.indices && nextCol1 in house[0].indices && house[nextRow1][nextCol1] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow1][nextCol1][1]) {
                            pq.offer(State(nextRow1, nextCol1, 1, currentMirrorCount + 1))
                            mirrorCounts[nextRow1][nextCol1][1] = currentMirrorCount + 1
                        }
                    }

                    val nextRow2 = row + dRow[0]
                    val nextCol2 = col + dCol[0]
                    if (nextRow2 in house.indices && nextCol2 in house[0].indices && house[nextRow2][nextCol2] != '*') {
                        if (currentMirrorCount < mirrorCounts[nextRow2][nextCol2][0]) {
                            pq.offer(State(nextRow2, nextCol2, 0, currentMirrorCount + 1))
                            mirrorCounts[nextRow2][nextCol2][0] = currentMirrorCount + 1
                        }
                    }
                }
            }
        }
    }
}