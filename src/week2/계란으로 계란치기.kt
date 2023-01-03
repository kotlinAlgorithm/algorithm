/**
 * https://www.acmicpc.net/problem/16987
 * 백준 16987번: 계란으로 계란치기
 */

package week2

var maxDestroyedEggCount = 0
lateinit var durabilityArray: IntArray
lateinit var weightArray: IntArray

fun main() {
    val eggSize = readln().trim().toInt()
    durabilityArray = IntArray(eggSize)
    weightArray = IntArray(eggSize)

    repeat(eggSize) { i ->
        val (durability, weight) = readln().trim().split(" ").map(String::toInt)
        durabilityArray[i] = durability
        weightArray[i] = weight
    }

    findDestroyedMaxEggCount(0, 0)
    print(maxDestroyedEggCount)
}

fun findDestroyedMaxEggCount(currentIndex: Int, destroyedEggCount: Int) {
    if (currentIndex == durabilityArray.size) {
        maxDestroyedEggCount = maxOf(maxDestroyedEggCount, destroyedEggCount)
        return
    }
    if (durabilityArray[currentIndex] <= 0 || destroyedEggCount >= durabilityArray.size - 1) {
        findDestroyedMaxEggCount(currentIndex + 1, destroyedEggCount)
        return
    }

    for (targetIndex in durabilityArray.indices) {
        if (targetIndex != currentIndex && durabilityArray[targetIndex] > 0) {
            durabilityArray[targetIndex] -= weightArray[currentIndex]
            durabilityArray[currentIndex] -= weightArray[targetIndex]

            var additionalDestroyedCount = 0
            if (durabilityArray[currentIndex] <= 0) additionalDestroyedCount++
            if (durabilityArray[targetIndex] <= 0) additionalDestroyedCount++
            findDestroyedMaxEggCount(currentIndex + 1, destroyedEggCount + additionalDestroyedCount)

            durabilityArray[targetIndex] += weightArray[currentIndex]
            durabilityArray[currentIndex] += weightArray[targetIndex]
        }
    }
}