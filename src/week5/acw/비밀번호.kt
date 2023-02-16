class `acw비밀번호` {
    const val MAX_X = 2
    const val MAX_Y = 3
    const val divisor = 1234567
    val arr = Array(4) { Array(3) { Array(1001) { 0L } } }

    data class Pos(var y: Int, var x: Int) {
        val dx = listOf(0, 0, -1, 1)
        val dy = listOf(1, -1, 0, 0)


        fun validate(y: Int, x: Int): Boolean {
            if (x > MAX_X || x < 0) return false
            if (y > MAX_Y || (y == MAX_Y && x != 0) || y < 0) return false

            return true
        }

        fun     addSurround(depth: Int) {
            for (i in 0 until 4) {
                val ny = y + dy[i]
                val nx = x + dx[i]
                if (validate(ny, nx)) arr[y][x][depth] = (arr[y][x][depth] + arr[ny][nx][depth - 1]) % divisor
            }
        }
    }


    fun initArr() {
        arr[3][0][2] = 1
        arr[0][0][2] = 2
        arr[0][1][2] = 3
        arr[0][2][2] = 2
        arr[1][0][2] = 3
        arr[1][1][2] = 4
        arr[1][2][2] = 3
        arr[2][0][2] = 3
        arr[2][1][2] = 3
        arr[2][2][2] = 2
    }

    fun solution(n: Int) {
        if (arr[0][0][n] == 0L) {
            for (depth in 3..n) {
                if (arr[0][0][depth] != 0L) {
                    continue
                }

                for (y in 0 until 3)
                    for (x in 0 until 3)
                        Pos(y, x).addSurround(depth)

                Pos(3, 0).addSurround(depth)
            }
        }


    }


    fun main() {
        val T = readln().toInt()
        initArr()

        for (i in 0 until T) {
            val N = readln().toInt()
            if (N == 1) {
                println(10)
                continue
            }

            solution(N)
            println((arr.sumOf { it.sumOf { it[N] } }) % divisor)
        }
    }

}
