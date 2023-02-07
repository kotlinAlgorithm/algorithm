class 공주님의정원 {
    import java.util.PriorityQueue

    /*
    시작시간으로 정렬하여 가장빠른 놈부터 선택하고
    선택한 놈의 종료시간전에 시작하는 꽃들 중 가장 긴 놈을 선택하고
    이 과정을 반복한다.
     */
    val flowerQ = PriorityQueue<Flower>(Comparator<Flower> { o1, o2 ->
        val a = o1.start - o2.start
        if (a == 0) {
            o2.end - o1.end
        } else {
            a
        }
    })

    data class Flower(var start: Time, var end: Time) {
        data class Time(var month: Int, var day: Int) : Comparable<Time> {
            operator fun minus(other: Time): Int {
                return ((this.month * 1000) + this.day) - ((other.month * 1000) + other.day)
            }

            override fun compareTo(other: Time): Int {
                val thisTime = this.month * 1000 + this.day
                val otherTime = other.month * 1000 + other.day

                return compareValues(thisTime, otherTime)
            }
        }


        constructor(inputArr: List<Int>) : this(
            start = Time(inputArr[0], inputArr[1]),
            end = Time(inputArr[2], inputArr[3])
        )
    }

    fun main() {
        val n = readln().toInt()
        for (i in 0 until n) {
            flowerQ.add(Flower(readln().split(" ").map { it.toInt() }))
        }
        val START_TIME = Flower.Time(3, 1)
        val END_TIME = Flower.Time(12, 1)

        var answer = 0
        var flowerNow = Flower(listOf(0, 0, 0, 0))
        var nextFlower = Flower(listOf(0, 0, 0, 0))


        while (flowerQ.isNotEmpty()) {
            val tmp = flowerQ.poll()
            if (tmp.start > START_TIME) {
                flowerQ.add(tmp)
                break
            }

            if (flowerNow.end < tmp.end) {
                flowerNow = tmp.copy()
                answer = 1
            }
        }//시작할 꽃 정하기_ 3월 1일이전이면서 가장 늦게끝나는 꽃을 선택함.


        while (flowerQ.isNotEmpty()) {
            if (flowerNow.end >= END_TIME) {
                break
            }//현재 선택된 꽃이 종료시간이상이면 바로 종료

            val tmpFlower = flowerQ.poll()
            if (tmpFlower.start <= flowerNow.end) {
                if (tmpFlower.end > nextFlower.end) {
                    nextFlower = tmpFlower.copy()
                }
                continue
            }//지금 선택된 꽃이 끝나기 전에 시작하고, 가장 늦게 끝나는 꽃을 선택하기

            if (nextFlower.end > flowerNow.end) {
                //지금 선택된 꽃의 범위를 넘는 꽃이 나올 경우 update하기
                answer++
                flowerNow = nextFlower.copy()
                nextFlower = Flower(listOf(0, 0, 0, 0))
                flowerQ.add(tmpFlower)//지금 뽑은 꽃은 다시 큐에 넣어주기
                continue
            }

        }

        if (nextFlower.end > flowerNow.end) {
            flowerNow = nextFlower.copy()
            answer++
        }//q순회 과정에서 nextFlower를 반영하지 않고 빠져나오는 경우

        if (flowerNow.end >= END_TIME) {
            println(answer)
        } else {
            println(0)
        }

    }
}
