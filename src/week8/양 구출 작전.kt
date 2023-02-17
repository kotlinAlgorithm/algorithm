package week8

import java.util.StringTokenizer

data class Island(val animal: String, var animalCount: Long)

private lateinit var linkedIslandIndexes: Array<MutableList<Int>>
private lateinit var islands: Array<Island>

fun main() {
    val islandCount = readln().toInt()
    linkedIslandIndexes = Array(islandCount) { mutableListOf()  }
    islands = arrayOf(Island("S", 0)) +
            Array(islandCount - 1) { i ->
                val st = StringTokenizer(readln())
                val animal = st.nextToken()
                val animalCount = st.nextToken().toLong()
                val linkedIslandIndex = st.nextToken().toInt() - 1
                linkedIslandIndexes[i + 1].add(linkedIslandIndex)
                linkedIslandIndexes[linkedIslandIndex].add(i + 1)
                Island(animal, animalCount)
            }

    print(moveAndComeBack(0, 0))
}

fun moveAndComeBack(idx: Int, prevIdx: Int): Long {
    var cameSheepCount = 0L
    for (linkedIdx in linkedIslandIndexes[idx]) {
        if (linkedIdx != prevIdx) {
            cameSheepCount += moveAndComeBack(linkedIdx, idx)
        }
    }

    val island = islands[idx]
    if (island.animal == "W") {
        val tempSheepCount = cameSheepCount
        cameSheepCount = maxOf(0, cameSheepCount - island.animalCount)
        island.animalCount = maxOf(0, island.animalCount - tempSheepCount)
    } else {
        cameSheepCount += island.animalCount
        island.animalCount = 0
    }

    return cameSheepCount
}