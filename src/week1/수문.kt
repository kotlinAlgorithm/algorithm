/**
 * https://www.acmicpc.net/problem/5638
 * 백준 5638번 수문
 */

package week1

import java.util.StringTokenizer

data class WaterDoor(val waterAmount: Int, val cost: Int)

lateinit var waterDoors: Array<WaterDoor>
var missionWaterAmount = 0
var timeLimit = 0
var minTotalCost = Long.MAX_VALUE

fun main() {
    val doorSize = readln().trim().toInt()
    waterDoors = Array(doorSize) {
        val (waterAmount, cost) = readln().trim().split(" ").map(String::toInt)
        WaterDoor(waterAmount, cost)
    }

    repeat(readln().trim().toInt()) { i ->
        val stringTokenizer = StringTokenizer(readln().trim())
        missionWaterAmount = stringTokenizer.nextToken().toInt()
        timeLimit = stringTokenizer.nextToken().toInt()
        minTotalCost = Long.MAX_VALUE
        findMinTotalCost(0, 0, 0)
        if (minTotalCost == Long.MAX_VALUE) {
            println("Case ${i + 1}: IMPOSSIBLE")
        } else {
            println("Case ${i + 1}: $minTotalCost")
        }
    }
}

fun findMinTotalCost(doorIndex: Int, accumulatedWaterAmount: Long, totalCost: Long) {
    if (doorIndex == waterDoors.size) {
        if (accumulatedWaterAmount * timeLimit >= missionWaterAmount) {
            minTotalCost = minOf(minTotalCost, totalCost)
        }
        return
    }

    findMinTotalCost(
        doorIndex = doorIndex + 1,
        accumulatedWaterAmount = accumulatedWaterAmount,
        totalCost = totalCost
    )
    findMinTotalCost(
        doorIndex = doorIndex + 1,
        accumulatedWaterAmount = accumulatedWaterAmount + waterDoors[doorIndex].waterAmount,
        totalCost = totalCost + waterDoors[doorIndex].cost
    )
}