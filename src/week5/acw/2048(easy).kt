class `acw2048` {
    lateinit var puzzle: Array<Array<Int>>

    fun up(n: Int, puzzleNow: Array<Array<Int>>): Array<Array<Int>> {
        val visit = Array(n) { Array(n) { false } }

        for (y in 0 until n) {
            for (x in 0 until n) {
                if (puzzleNow[y][x] == 0) continue

                var yn = y
                while (yn > 0) {
                    if (puzzleNow[yn - 1][x] == 0) {
                        puzzleNow[yn - 1][x] = puzzleNow[yn][x]
                        puzzleNow[yn][x] = 0
                        yn--
                        //빈칸일 땐 밀어넣기
                    } else if (puzzleNow[yn - 1][x] > 0) {
                        if ((puzzleNow[yn - 1][x] == puzzleNow[yn][x]) && !visit[yn - 1][x] && !visit[yn][x]) {
                            //합치기
                            puzzleNow[yn - 1][x] *= 2
                            puzzleNow[yn][x] = 0
                            visit[yn - 1][x] = true
                            break
                        } else {
                            break
                        }
                    }

                }
            }
        }
        return puzzleNow
    }

    fun down(n: Int, puzzleNow: Array<Array<Int>>): Array<Array<Int>> {
        val visit = Array(n) { Array(n) { false } }

        for (y in n - 1 downTo 0) {
            for (x in 0 until n) {
                if (puzzleNow[y][x] == 0) continue

                var yn = y
                while (yn < n - 1) {
                    if (puzzleNow[yn + 1][x] == 0) {
                        puzzleNow[yn + 1][x] = puzzleNow[yn][x]
                        puzzleNow[yn][x] = 0
                        yn++
                        //빈칸일 땐 밀어넣기
                    } else if (puzzleNow[yn + 1][x] > 0) {
                        if ((puzzleNow[yn + 1][x] == puzzleNow[yn][x]) && !visit[yn + 1][x] && !visit[yn][x]) {
                            //합치기
                            puzzleNow[yn + 1][x] += puzzleNow[yn][x]
                            puzzleNow[yn][x] = 0
                            visit[yn + 1][x] = true
                            break
                        } else {
                            break
                        }
                    }

                }
            }
        }
        return puzzleNow

    }

    fun right(n: Int, puzzleNow: Array<Array<Int>>): Array<Array<Int>> {
        val visit = Array(n) { Array(n) { false } }

        for (x in n - 1 downTo 0) {
            for (y in 0 until n) {
                if (puzzleNow[y][x] == 0) continue

                var xn = x
                while (xn < n - 1) {
                    if (puzzleNow[y][xn + 1] == 0) {
                        puzzleNow[y][xn + 1] = puzzleNow[y][xn]
                        puzzleNow[y][xn] = 0
                        xn++
                        //빈칸일 땐 밀어넣기
                    } else if (puzzleNow[y][xn + 1] > 0) {
                        if ((puzzleNow[y][xn + 1] == puzzleNow[y][xn]) && !visit[y][xn + 1] && !visit[y][xn]) {
                            //합치기
                            puzzleNow[y][xn + 1] += puzzleNow[y][xn]
                            puzzleNow[y][xn] = 0
                            visit[y][xn + 1] = true
                            break
                        } else {
                            break
                        }
                    }

                }
            }
        }
        return puzzleNow

    }

    fun left(n: Int, puzzleNow: Array<Array<Int>>): Array<Array<Int>> {
        val visit = Array(n) { Array(n) { false } }
        for (x in 0 until n) {
            for (y in 0 until n) {
                if (puzzleNow[y][x] == 0) continue

                var xn = x
                while (xn > 0) {
                    if (puzzleNow[y][xn - 1] == 0) {
                        puzzleNow[y][xn - 1] = puzzleNow[y][xn]
                        puzzleNow[y][xn] = 0
                        xn--
                        //빈칸일 땐 밀어넣기
                    } else if (puzzleNow[y][xn - 1] > 0) {
                        if ((puzzleNow[y][xn - 1] == puzzleNow[y][xn]) && !visit[y][xn - 1]) {
                            //합치기
                            puzzleNow[y][xn - 1] += puzzleNow[y][xn]
                            puzzleNow[y][xn] = 0
                            visit[y][xn - 1] = true
                            break
                        } else {
                            break
                        }
                    }

                }
            }
        }
        return puzzleNow

    }


    val q = arrayListOf<Pair<Array<Array<Int>>, Int>>()
    fun main() {
        val N = readln().toInt()
        puzzle = Array(N) { readln().split(" ").map { it.toInt() }.toTypedArray() }


        q.add(Pair(puzzle, 0))
        var answer = 0
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            if (now.second == 6) {
                break
            }


            val maxVal = now.first.maxOf { it.maxOf { it } }
            if (maxVal > answer) {
                answer = maxVal
            }

            val arrUp = Array(N) { Array(N) { 0 } }
            val arrDown = Array(N) { Array(N) { 0 } }
            val arrRight = Array(N) { Array(N) { 0 } }
            val arrLeft = Array(N) { Array(N) { 0 } }

            for (i in 0 until N) {
                for (j in 0 until N) {
                    arrUp[i][j] = now.first[i][j]
                    arrDown[i][j] = now.first[i][j]
                    arrRight[i][j] = now.first[i][j]
                    arrLeft[i][j] = now.first[i][j]
                }
            }

            q.add(Pair(up(N, arrUp), now.second + 1))
            q.add(Pair(down(N, arrDown), now.second + 1))
            q.add(Pair(right(N, arrRight), now.second + 1))
            q.add(Pair(left(N, arrLeft), now.second + 1))
        }

        println(answer)

    }

}
