package week1.acw

import java.util.*
import java.util.PriorityQueue

const val FREEZING_TIME = 181

class `acw역전의제왕` {

    data class History(
        var time: Time,
        var problemNum: Int,
        var submitCnt: Int,
        var historyNum: Int
    )

    class Time(time: String) {
        val hour: Int
        val min: Int

        init {
            val (h, m) = time.split(":").map { it.toInt() }
            hour = h
            min = m
        }

        fun getTimeInMin(): Int {
            return hour * 60 + min
        }

    }

    data class Player(
        var playerNum: Int,
        var lastSubmitTime: Int,
        var score: Int,
        var penalty: Int,
        var point: Int,
        var sumOfHistoryNum: Int
    )

    fun getPenalty(submitTime: Time, submitCnt: Int): Int {
        return submitTime.getTimeInMin() + (submitCnt - 1) * 20
    }

    fun solution() {
        val (numOfPeople, numOfHistory) = readln().split(" ").map { it.toInt() }
        val histories = Array(numOfPeople + 1) { PriorityQueue<History>(compareBy { it.problemNum }) }
        val players = Array(numOfPeople + 1) { Player(it, 0, 0, 0, 0, 0) }
        val rank =
            PriorityQueue<Player>(compareBy<Player> { it.score }.thenBy { -it.penalty }.thenBy { -it.lastSubmitTime }
                .thenBy { -it.sumOfHistoryNum })
        var historyNumInFreezingTime = 0

        repeat(numOfHistory) {
            val (time, playerNum, problemNum, submitCnt) = readln().split(" ")
            val history = History(Time(time), problemNum.toInt(), submitCnt.toInt(), it)

            if (history.time.getTimeInMin() < FREEZING_TIME) {
                players[playerNum.toInt()].apply {
                    penalty += getPenalty(history.time, history.submitCnt)
                    score++
                    if (history.time.getTimeInMin() > lastSubmitTime) {
                        lastSubmitTime = history.time.getTimeInMin()
                    }
                    sumOfHistoryNum += history.historyNum
                }

            } else {
                histories[playerNum.toInt()].add(history)
                historyNumInFreezingTime++
            }
        }//입력처리

        rank.addAll(players)

        var prevPlayer = 0
        for (i in 0 until historyNumInFreezingTime) {
            while (histories[rank.peek().playerNum].isEmpty()) {
                rank.poll()
            }//꼴찌가 확정일 경우 ranking계산에서 삭제

            val playerNow = rank.poll()
            val history = histories[playerNow.playerNum].poll()
            println(playerNow)
            players[playerNow.playerNum].apply {
                penalty += getPenalty(history.time, history.submitCnt)
                score++
                if (history.time.getTimeInMin() > lastSubmitTime) {
                    lastSubmitTime = history.time.getTimeInMin()
                }
                sumOfHistoryNum += history.historyNum

            }

            prevPlayer = playerNow.playerNum
            rank.add(players[playerNow.playerNum])

            if (rank.peek().playerNum != prevPlayer) {
                val tmp = arrayListOf<Player>()
                var cnt = 0
                while (rank.peek().playerNum != prevPlayer) {
                    tmp.add(rank.poll())
                    cnt++
                }
                players[prevPlayer].point += cnt
                rank.addAll(tmp)

            }//rank상승 계산해서 point반영

        }
        players.sortBy { -it.point }
        rank.clear()

        val maxPoint = players[0].point
        rank.addAll(players.filter { it.point == maxPoint })

        while (rank.size != 1) rank.poll()
        println(rank.peek().playerNum)
        println(players.joinToString())
        println(rank.size)


    }
}

fun main() {
    val sol = `acw역전의제왕`()
    sol.solution()
}