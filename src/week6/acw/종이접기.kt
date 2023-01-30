class 종이접기 {
    var answer = true
    fun sol(paper: String, start: Int, end: Int) {
        if (start >= end) {
            return
        }
        val mid = (start + end) / 2
        var left = start
        var right = end

        while (left < right) {
            if (paper[left] == paper[right]) {
                answer = false
                return
            }
            left++
            right--
        }

        sol(paper, start, mid - 1)
        sol(paper, mid + 1, end)


    }

    fun main() {
        val n = readln().toInt()

        for (i in 0 until n) {
            val paper = readln().trim()
            answer = true
            sol(paper, 0, paper.length - 1)
            if (answer) println("YES")
            else println("NO")
        }

    }
}
