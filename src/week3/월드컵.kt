package week3

data class Match(val leftNationIndex: Int, val rightNationIndex: Int)

val stats = IntArray(18)
val matchUps = mutableListOf<Match>()
val testCases= mutableListOf<List<Int>>()
val isPossibleTestCase = MutableList<Int>(4) { 0 }

fun main() {
    receiveTestCases()
    createAllMatchUps()
    play(0)
    print(isPossibleTestCase.joinToString(" "))
}

fun receiveTestCases() {
    repeat(4) {
        testCases.add(readln().split(" ").map(String::toInt))
    }
}

fun createAllMatchUps() {
    for (leftNationIndex in 0 until 6) {
        for (rightNationIndex in leftNationIndex + 1 until 6) {
            matchUps.add(Match(leftNationIndex, rightNationIndex))
        }
    }
}

fun play(matchIndex: Int) {
    if (matchIndex == matchUps.size) {
        compareStatsWithTestCases()
        return
    }

    val currentMatchUp = matchUps[matchIndex]

    stats[3 * currentMatchUp.leftNationIndex]++
    stats[3 * currentMatchUp.rightNationIndex + 2]++
    play(matchIndex + 1)
    stats[3 * currentMatchUp.leftNationIndex]--
    stats[3 * currentMatchUp.rightNationIndex + 2]--

    stats[3 * currentMatchUp.leftNationIndex + 1]++
    stats[3 * currentMatchUp.rightNationIndex + 1]++
    play(matchIndex + 1)
    stats[3 * currentMatchUp.leftNationIndex + 1]--
    stats[3 * currentMatchUp.rightNationIndex + 1]--

    stats[3 * currentMatchUp.leftNationIndex + 2]++
    stats[3 * currentMatchUp.rightNationIndex]++
    play(matchIndex + 1)
    stats[3 * currentMatchUp.leftNationIndex + 2]--
    stats[3 * currentMatchUp.rightNationIndex]--
}

fun compareStatsWithTestCases() {
    for ((i, testCase) in testCases.withIndex()) {
        var isMatched = true

        for (j in 0 until 18) {
            if (stats[j] != testCase[j]) {
                isMatched = false
                break
            }
        }

        if (isMatched) {
            isPossibleTestCase[i] = 1
        }
    }
}