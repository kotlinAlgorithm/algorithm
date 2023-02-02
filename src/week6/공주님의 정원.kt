package week6

/**
 * https://www.acmicpc.net/problem/2457
 */
data class Flower(val startNumber: Int, val endNumber: Int)

fun main() {
    val flowerCount = readln().trim().toInt()
    val flowers = mutableListOf<Flower>()

    repeat(flowerCount) {
        val dates = readln().trim().split(" ").map(String::toInt)
        flowers.add(Flower(dates[0] * 100 + dates[1], dates[2] * 100 + dates[3])) // (월 * 100 + 일)로 변환
    }

    flowers.sortBy { it.startNumber }
    var currentFlower = Flower(0, 301) // 3월 1일에 피는 꽃을 찾기 위한 초기 값
    var minFlowerCount = 0

    while (currentFlower.endNumber <= 1130) {
        var longestFlower = currentFlower

        for (j in 0 until flowers.size) {
            // 현재 꽃의 개화 기간과 겹치는 꽃을 선택해야 함
            if (flowers[j].startNumber <= currentFlower.startNumber) continue
            if (flowers[j].startNumber > currentFlower.endNumber) break

            // 가장 늦게 지는 꽃을 고른다
            if (flowers[j].endNumber > longestFlower.endNumber) {
                longestFlower = flowers[j]
            }
        }

        if (longestFlower == currentFlower) {
            break
        }
        currentFlower = longestFlower
        minFlowerCount++
    }

    if (currentFlower.endNumber < 1201) {
        print(0)
    } else {
        print(minFlowerCount)
    }
}