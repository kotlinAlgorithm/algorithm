class `벽부수고이동하기4` {
    lateinit var mapArr: Array<Array<Int>>
    lateinit var visit: Array<Array<Boolean>>
    var n = 0;
    var m = 0
    val q = mutableSetOf<Pair<Int, Int>>()

    val dy = listOf<Int>(-1, 1, 0, 0)
    val dx = listOf<Int>(0, 0, 1, -1)

    var sum = 0

    fun main() {
        val (N, M) = readln().split(" ").map { it.toInt() }
        n = N; m = M
        mapArr = Array(N) { readln().chunked(1).map { it.toInt() }.toTypedArray() }
        visit = Array(N) { Array(M) { false } }


        for (y in 0 until n) {
            for (x in 0 until m) {
                if (mapArr[y][x] == 0 && !visit[y][x]) {
                    sum = 0
                    dfs(y, x)
                    for (i in q) {
                        val (ty, tx) = i
                        mapArr[ty][tx] += sum
                    }
                    q.clear()
                }
            }
        }


        for (y in 0 until n) {
            println(mapArr[y].joinToString("", transform = { (it % 10).toString() }))
        }


    }


    fun dfs(y: Int, x: Int) {
        if (y < 0 || y > n - 1 || x < 0 || x > m - 1) {
            return
        }
        if (visit[y][x]) return


        if (mapArr[y][x] > 0) {
            q.add(Pair(y, x))
            return
        }
        visit[y][x] = true
        sum++

        for (i in 0 until 4) {
            val ny = y + dy[i]
            val nx = x + dx[i]
            dfs(ny, nx)
        }

    }
}
