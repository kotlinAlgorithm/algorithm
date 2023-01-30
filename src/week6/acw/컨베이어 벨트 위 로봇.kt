class 컨베이어벨트위로봇 {
    lateinit var conveyor: Array<Array<Int>>
    var n = 0
    var k = 0
    val robotQ = arrayListOf<Pos>()
    var numOfZero = 0
    var depth = 0

    data class Pos(var y: Int, var x: Int)

    //step 1
    fun rotate(): Int {
        val newQ = arrayListOf<Pos>()
        while (robotQ.isNotEmpty()) {
            val r = robotQ.removeFirst()
            when (r.y) {
                0 -> {
                    if (r.x == n - 2) {
                        continue
                        //빠지는 로봇
                    } else {
                        newQ.add(Pos(0, r.x + 1))
                        if (reduceDurability(0, r.x + 1) < 0) return -1
                    }
                }

                1 -> {
                    if (r.x == 0) {
                        newQ.add(Pos(0, 0))
                        if (reduceDurability(0, 0) < 0) return -1
                    } else {
                        newQ.add(Pos(1, r.x - 1))
                        if (reduceDurability(0, r.x - 1) < 0) return -1
                    }
                }
            }
        }
        robotQ.addAll(newQ)
        newQ.clear()
        return 0
    }

    //step 2
    fun moveRobot(): Int {
        val newQ = arrayListOf<Pos>()
        while (robotQ.isNotEmpty()) {
            val r = robotQ.removeFirst()
            when (r.y) {
                0 -> {
                    if (conveyor[0][r.x + 1] < 1) {
                        //내구도 없으면 이동 x
                        newQ.add(r)
                    }

                    if (r.x == n - 2) {
                        //빠지는 로봇
                        if (reduceDurability(0, n - 1) < 0) return -1
                        continue
                    } else {
                        newQ.add(Pos(0, r.x + 1))
                        if (reduceDurability(0, r.x + 1) < 0) return -1
                        continue
                    }
                }

                1 -> {
                    if (r.x == 0) {
                        //1,0일 때 이동 처리해주기
                        if (conveyor[0][0] < 1) {
                            newQ.add(r)
                        } else {
                            newQ.add(Pos(0, 0))
                            if (reduceDurability(0, 0) < 0) return -1
                        }
                        continue
                    }

                    if (conveyor[1][r.x - 1] < 1) {
                        //내구도 없으면 이동 x
                        newQ.add(r)
                        continue
                    }

                    newQ.add(Pos(1, r.x - 1))
                    if (reduceDurability(1, r.x - 1) < 0) return -1
                }
            }
        }
        robotQ.addAll(newQ)
        newQ.clear()
        return 0
    }

    fun reduceDurability(y: Int, x: Int): Int {
        if (conveyor[y][x] == 1) {
            numOfZero++
            println(numOfZero)
            println("$y $x")
        }
        conveyor[y][x]--

        return if (numOfZero == k) -1
        else 1
    }

    //step 3
    fun inputRobot(): Int {
        for (i in robotQ) {
            if (i.y == 0 && i.x == 0) {
                return 0
            }
        }//이미 로봇이 있을 경우 올리지 않기

        if (conveyor[0][0] > 0) {
            robotQ.add(Pos(0, 0))
            return reduceDurability(0, 0)
        } else {
            return 0
            //내구도가 없을 경우 올리지 않기
        }
    }

    fun main() {
        val (i1, i2) = readln().split(" ").map { it.toInt() }
        n = i1;k = i2

        conveyor = Array(2) { Array(n) { 0 } }
        val durability = readln().split(" ").map { it.toInt() }

        for (i in 0 until n) {
            conveyor[0][i] = durability[i]
        }
        for (i in n until 2 * n) {
            conveyor[1][2 * n - i - 1] = durability[i]
        }
        println(conveyor[0].joinToString())
        println(conveyor[1].joinToString())

        while (true) {
            depth++
            if (rotate() < 0) break
            if (moveRobot() < 0) break
            if (inputRobot() < 0) break
            println(conveyor[0].joinToString())
            println(conveyor[1].joinToString())
            println(robotQ)

        }
        println(depth)

    }
}
