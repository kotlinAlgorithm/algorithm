/**
 * https://www.acmicpc.net/problem/22945
 * 백준 22945번: 팀 빌딩
 */

package week2

import java.util.StringTokenizer

fun main() {
    val n = readln().trim().toInt()
    val status = with(StringTokenizer(readln().trim())){
        IntArray(n) {
            this.nextToken().toInt()
        }
    }
    var start = 0
    var end = n - 1
    var maxTeamStatus = 0

    while (end - start > 1) {
        val teamStatus = (end - start - 1) * minOf(status[start], status[end])
        maxTeamStatus = maxOf(maxTeamStatus, teamStatus)

        if (status[start] < status[end]) {
            start++
        } else {
            end--
        }
    }

    print(maxTeamStatus)
}
