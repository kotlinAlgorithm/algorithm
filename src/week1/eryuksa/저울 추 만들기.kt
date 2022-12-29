/**
 * https://www.acmicpc.net/problem/2205
 * 백준 2205번 저울 추 만들기
 */

package week1.eryuksa

import java.util.LinkedList

fun main() {
    val n = readln().trim().toInt()
    val answerQueue = LinkedList<Int>()
    val isPairedSn = BooleanArray(n + 1)
    var powerOf2 = 2

    while (powerOf2 <= n) {
        powerOf2 *= 2
    }

    for (pb in n downTo 1) {
        var currentPowerOf2 = powerOf2
        var sn = currentPowerOf2 - pb
        while (sn > n || isPairedSn[sn]) {
            currentPowerOf2 /= 2
            sn = currentPowerOf2 - pb
        }

        isPairedSn[sn] = true
        answerQueue.addFirst(sn)
    }

    print(answerQueue.joinToString("\n"))
}