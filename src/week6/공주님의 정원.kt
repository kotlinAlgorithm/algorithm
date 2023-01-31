package week6

data class Flower(val startNumber: Int, val endNumber: Int)

fun main() {
    val flowerCount = readln().trim().toInt()
    val flowers = mutableListOf<Flower>()
    repeat(flowerCount) {
        val dates = readln().trim().split(" ").map(String::toInt)
        if (dates[0] <= 11 && dates[2] >= 3) {
            if (dates[0] < 3 && dates[2] > 11) {  // 3월 이전에 피고 12월에 지는 꽃이 있는 경우
                print(1)
                return
            } else if (dates[0] < 3) { // 3월 이전에 피는 꽃을 3월1일로 변경한다
                flowers.add(Flower(301, dates[2] * 100 + dates[3]))
            } else if (dates[2] > 11) { // 12월에 피는 꽃을 12월1일로 변경한다
                flowers.add(Flower(dates[0] * 100 + dates[1], 1201))
            } else {
                flowers.add(Flower(dates[0] * 100 + dates[1], dates[2] * 100 + dates[3])) // (월 * 100 + 일)로 변환
            }
        }
    }

    flowers.sortBy { it.startNumber }
    var currentFlower = Flower(startNumber = 300, endNumber = 301) // 3월 1일에 피는 꽃을 찾기 위한 초기 값
    var currentFlowerIndex = -1
    var minFlowerCount = 0
    for (i in flowers.indices) {
        if (i <= currentFlowerIndex) continue

        var tempFlower = currentFlower
        var tempFlowerIndex = currentFlowerIndex
        for (j in currentFlowerIndex + 1 until flowers.size) {
            // 현재 꽃보다 늦게 피고, 지기 전에 피는 것 중
            if (flowers[j].startNumber in currentFlower.startNumber + 1..currentFlower.endNumber) {
                if (flowers[j].endNumber > tempFlower.endNumber) { // 가장 늦게 지는 꽃을 고른다
                    tempFlower = flowers[j]
                    tempFlowerIndex = j
                }
            }
        }

        if (tempFlower == currentFlower) { // 중간에 피는 꽃이 없거나 더 늦게 지는 꽃이 없는 경우
            break
        }

        minFlowerCount++
        currentFlower = tempFlower
        currentFlowerIndex = tempFlowerIndex
    }

    if (currentFlower.endNumber < 1201) {
        print(0)
    } else {
        print(minFlowerCount)
    }
}