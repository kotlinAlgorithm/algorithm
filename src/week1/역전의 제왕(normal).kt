/**
 * https://www.acmicpc.net/problem/20417
 * 백준 20417번 역전의 제왕
 */
package week1

import java.util.*

data class Player(
    val index: Int,
    var solveCount: Int,
    var penalty: Int,
    var lastSubmissionMinute: Int,
    var lastSubmissionOrder: Int,
    var reverseScore: Int,
    val frozenSubmissionQueue: PriorityQueue<SubmissionInfo>
)
data class SubmissionInfo(val submissionMinute: Int, val problemNumber: Int, val submissionCount: Int, val submissionOrder: Int)

fun main() {
    val (playerSize, submissionSize) = readln().trim().split(" ").map(String::toInt)
    val playerList = MutableList<Player>(playerSize) { i ->
        Player(
            index = i,
            solveCount =0,
            penalty = 0,
            lastSubmissionMinute = 0,
            lastSubmissionOrder = 0,
            reverseScore = 0,
            frozenSubmissionQueue = PriorityQueue<SubmissionInfo>(compareBy { info -> info.problemNumber })
        )
    }

    var frozenSubmissionCount = 0

    repeat(submissionSize) { i ->
        val input = readln().trim().split(" ")
        val minute = with(input[0].split(":").map(String::toInt)) {
            this[0] * 60 + this[1]
        }
        val playerIndex = input[1].toInt() - 1
        val submissionCount = input[3].toInt()
        val submissionInfo = SubmissionInfo(
            submissionMinute = minute,
            problemNumber = input[2].toInt(),
            submissionCount = submissionCount,
            submissionOrder = i
        )

        if (minute > 180) {
            frozenSubmissionCount++
            playerList[playerIndex].frozenSubmissionQueue.offer(submissionInfo)
        } else {
            val penalty = minute + (submissionCount - 1) * 20
            playerList[playerIndex].apply {
                solveCount++
                this.penalty += penalty
                lastSubmissionMinute = minute
                lastSubmissionOrder = i
            }
        }

    }

    playerList.sortWith(
        compareByDescending<Player> { it.solveCount }
            .thenBy { it.penalty }
            .thenBy { it.lastSubmissionOrder }
    )
    var lastPlaceIndex = playerSize - 1
    var lastPlacedPlayer: Player = playerList.last()

    while (frozenSubmissionCount > 0) {
        if (lastPlacedPlayer.frozenSubmissionQueue.isEmpty()) {
            lastPlaceIndex -= 1
            lastPlacedPlayer = playerList[lastPlaceIndex]
            continue
        }

        val submissionInfo = lastPlacedPlayer.frozenSubmissionQueue.poll()
        lastPlacedPlayer.apply {
            solveCount++
            penalty += submissionInfo.submissionMinute + (submissionInfo.submissionCount - 1) * 20
            lastSubmissionMinute = maxOf(lastSubmissionMinute, submissionInfo.submissionMinute)
            lastSubmissionOrder = maxOf(lastSubmissionOrder, submissionInfo.submissionOrder)
        }

        playerList.sortWith(
            compareByDescending<Player> { it.solveCount }
                .thenBy { it.penalty }
                .thenBy { it.lastSubmissionOrder }
        )
        val changedRank = playerList.indexOf(lastPlacedPlayer)
        lastPlacedPlayer.reverseScore += lastPlaceIndex - changedRank

        frozenSubmissionCount--
        lastPlacedPlayer = playerList[lastPlaceIndex]
    }

    playerList.sortWith(compareByDescending { it.reverseScore })
    print(playerList[0].index + 1)
}