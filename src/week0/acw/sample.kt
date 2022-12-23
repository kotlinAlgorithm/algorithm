class 단풍잎이야기 {
    lateinit var quests: Array<Array<Int>>
    lateinit var comb: Array<Boolean>
    private var answer = 0

    private fun makeComb(arr: MutableList<Int>, targetNum: Int, count: Int, start: Int) {
        if (count == targetNum) {

            for (i in 1 until 2 * targetNum + 1) {
                if (comb[i]) {
                    arr.add(i)
                }
            }
            answer = answer.coerceAtLeast(quests.count { quest -> arr.containsAll(quest.toList()) })

            arr.clear()
            return
        }

        for (i in start..2 * targetNum) {
            comb[i] = true
            makeComb(arr, targetNum, count + 1, i + 1)
            comb[i] = false
        }
    }

    fun solution() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        quests = Array(m) { readln().split(" ").map { it.toInt() }.toTypedArray() }
        comb = Array(2 * n + 1) { false }

        makeComb(mutableListOf(), n, 0, 1)

        println(answer)


    }
}

fun main() {
    val sol = 단풍잎이야기()
    sol.solution()
}