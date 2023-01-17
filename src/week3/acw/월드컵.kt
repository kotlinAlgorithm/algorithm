class 월드컵 {
    data class Result(
        var win: Int,
        var draw: Int,
        var lose: Int,
    ) {

        constructor(list: List<Int>, countryNum: Int) : this(list[0], list[1], list[2])

        fun isSameValue(otherResult: Result): Boolean {
            return otherResult.win == win && otherResult.draw == draw && otherResult.lose == lose
        }

    }

    val matchList = mutableListOf<Pair<Int, Int>>()
    var answer = false
    fun main() {
        val answerList = mutableListOf<Int>()
        makeMatch()
        repeat(4) {

            val input = readln().split(" ").map { it.toInt() }.chunked(3)
            val listOfResult = mutableListOf<Result>().apply {
                addAll(input.mapIndexed { index, list -> Result(list, index) })
            }
            answer = false

            bruteForceFind(listOfResult, List<Result>(6) { Result(0, 0, 0) }, 0)


            if (answer) {
                answerList.add(1)
            } else {
                answerList.add(0)
            }

        }

        println(answerList.joinToString(" "))

    }

    fun makeMatch() {
        for (i in 0..5) {
            for (j in i + 1..5) {
                matchList.add(Pair(i, j))
            }
        }
    }


    fun bruteForceFind(matchResultList: List<Result>, resultList: List<Result>, depth: Int) {
        if (depth == 15) {
            if (answer) return

            for (i in 0..5) {
                if (!matchResultList[i].isSameValue(resultList[i])) return
            }

            answer = true
            return
        }
        val team1 = matchList[depth].first
        val team2 = matchList[depth].second

        resultList[team1].win++
        resultList[team2].lose++
        bruteForceFind(matchResultList, resultList, depth + 1)
        resultList[team1].win--
        resultList[team2].lose--

        resultList[team1].lose++
        resultList[team2].win++
        bruteForceFind(matchResultList, resultList, depth + 1)
        resultList[team1].lose--
        resultList[team2].win--

        resultList[team1].draw++
        resultList[team2].draw++
        bruteForceFind(matchResultList, resultList, depth + 1)
        resultList[team1].draw--
        resultList[team2].draw--

    }
}

