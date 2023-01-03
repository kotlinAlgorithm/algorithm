/**
 * https://www.acmicpc.net/problem/15724
 * 백준 15724번: 주지수
 */

package week2

import java.util.StringTokenizer

fun main() {
    var stringTokenizer = StringTokenizer(readln().trim())
    val rowSize = stringTokenizer.nextToken().toInt()
    val columnSize = stringTokenizer.nextToken().toInt()
    val populationGraph = Array(rowSize) {
        stringTokenizer = StringTokenizer(readln().trim())
        IntArray(columnSize) {
            stringTokenizer.nextToken().toInt()
        }
    }
    val prefixSumGraph = Array(rowSize + 1) {
        IntArray(columnSize + 1)
    }

    for (row in 1 until rowSize + 1) {
        for (column in 1 until columnSize + 1) {
            prefixSumGraph[row][column] = populationGraph[row - 1][column - 1] +
                        prefixSumGraph[row - 1][column] + prefixSumGraph[row][column - 1] -
                        prefixSumGraph[row - 1][column - 1]
        }
    }

    repeat(readln().trim().toInt()) {
        val (row1, column1, row2, column2) = readln().trim().split(" ").map(String::toInt)
        println(
            prefixSumGraph[row2][column2] -
                prefixSumGraph[row1 - 1][column2] - prefixSumGraph[row2][column1 - 1] +
                prefixSumGraph[row1 - 1][column1 - 1]
        )
    }
}