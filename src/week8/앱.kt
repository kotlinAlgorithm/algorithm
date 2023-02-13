package week8

fun main() {
    val (appCount, necessaryMemory) = readln().trim().split(" ").map(String::toInt)
    val memories = listOf(0) + readln().split(" ").map(String::toInt)
    val cost = listOf(0) + readln().split(" ").map(String::toInt)
    val maxMemories = Array(appCount + 1) { IntArray(appCount * 100 + 1) }

    for (i in 1..appCount) {
        for (currentCost in 1..appCount * 100) {
            maxMemories[i][currentCost] = if (currentCost >= cost[i]) {
                maxOf(maxMemories[i - 1][currentCost - cost[i]] + memories[i], maxMemories[i - 1][currentCost])
            } else {
                maxMemories[i - 1][currentCost]
            }
        }
    }

    print(maxMemories[appCount].indexOfFirst{ availableMemory -> availableMemory >= necessaryMemory })
}
