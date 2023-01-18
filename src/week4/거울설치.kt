class `거울설치` {
    var start = Pair(-1, -1);
    var end = Pair(0, 0)
    lateinit var house: Array<Array<String>>
    lateinit var visited: Array<Array<Boolean>>
    val q = mutableListOf<Light>()

    data class Light(var y: Int, var x: Int, var direction: Int, var mirrorCnt: Int = 0, var depth: Int = 0)

    fun main() {
        val n = readln().toInt()
        house = Array(n + 2) { Array(n + 2) { " " } }
        visited = Array(n + 1) { Array(n + 1) { false } }

        val input = Array(n) { readln().chunked(1).toTypedArray() }

        for (i in 1..n) {
            for (j in 1..n) {
                house[i][j] = input[i - 1][j - 1]
                if (house[i][j] == "#") {
                    if (start.first == -1) {
                        start = Pair(i, j)
                    } else {
                        end = Pair(i, j)
                    }
                }
            }
        }//input

        println(findMinWay(n))

    }

    fun findMinWay(n: Int): Int {

        if (start.second > 1) {
            q.add(Light(start.first + 1, start.second - 1, 45, 0, 1))
        }
        if (start.second < n) {
            q.add(Light(start.first + 1, start.second + 1, -45, 1))
        }
        q.add(Light(start.first + 1, start.second, 90, 1, 1))

        var stopDepth = 100
        var minMirror = 100
        while (q.isNotEmpty()) {
            val lightNow = q.removeLast()

            if (lightNow.depth > stopDepth) {
                break
            }

            if (lightNow.y == end.first && lightNow.x == end.second) {
                if (minMirror > lightNow.mirrorCnt) {
                    minMirror = lightNow.mirrorCnt
                }
                stopDepth = lightNow.depth
            }


            if (lightNow.y < 1 || lightNow.y > n - 1 || lightNow.x < 1 || lightNow.x > n - 1) {
                continue
            }

            if (visited[lightNow.y][lightNow.x]) {
                continue
            }
            visited[lightNow.y][lightNow.x] = true

            if (house[lightNow.y][lightNow.x] == "!") {
                if (lightNow.direction == -45) {
                    q.add(Light(lightNow.y + 1, lightNow.x - 1, 45, lightNow.mirrorCnt + 1, lightNow.depth + 1))
                    //거울이 45일 경우

                    q.add(Light(lightNow.y + 1, lightNow.x + 1, -45, lightNow.mirrorCnt, lightNow.depth + 1))
                    //거울이 -45이거나 거울이 없다면 진행방향그대로 진행
                } else if (lightNow.direction == 45) {
                    q.add(Light(lightNow.y + 1, lightNow.x + 1, -45, lightNow.mirrorCnt + 1, lightNow.depth + 1))
                    //거울이 -45일 경우

                    q.add(Light(lightNow.y + 1, lightNow.x - 1, 45, lightNow.mirrorCnt, lightNow.depth + 1))
                    //거울이 -45이거나 거울이 없다면 진행방향그대로 진행


                } else {
                    q.add(Light(lightNow.y + 1, lightNow.x, 90, lightNow.mirrorCnt, lightNow.depth + 1))
                }


            } else {
                when (lightNow.direction) {//거울이 없을 땐 진행방향으로 그냥 진행
                    90 -> {
                        q.add(Light(lightNow.y + 1, lightNow.x, lightNow.direction, lightNow.depth + 1))
                    }

                    45 -> {
                        q.add(Light(lightNow.y + 1, lightNow.x - 1, lightNow.direction, lightNow.depth + 1))
                    }

                    -45 -> {
                        q.add(Light(lightNow.y + 1, lightNow.x + 1, lightNow.direction, lightNow.depth + 1))
                    }
                }
            }

        }

        return minMirror

    }
}
