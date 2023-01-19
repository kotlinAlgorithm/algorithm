class `ACM Craft`{
    fun decideCost(
        visit: Array<Boolean>,
        buildingNum: Int,
        childs: Array<MutableList<Int>>,
        buildCost: MutableList<Int>
    ): Int {
        if (childs[buildingNum].size == 0) {
            return buildCost[buildingNum - 1]
        } else {
            var maxChild = 0
            for (child in childs[buildingNum]) {
                if (visit[child] && buildCost[child - 1] > maxChild) {
                    maxChild = buildCost[child - 1]
                    continue
                } else {
                    visit[child] = true
                    val c = decideCost(visit, child, childs, buildCost)
                    if (c > maxChild) maxChild = c
                }
            }
            return buildCost[buildingNum - 1] + maxChild
        }
    }

    fun main() {
        val N = readln().toInt()

        repeat(N) {
            val (numOfBuilding, numOfRule) = readln().split(" ").map { it.toInt() }
            val buildingArr = readln().split(" ").map { it.toInt() }.toMutableList()
            val childs = Array(numOfBuilding + 1) { mutableListOf<Int>() }

            for (i in 0 until numOfRule) {
                val (a, b) = readln().split(" ").map { it.toInt() }
                childs[b].add(a)
            }

            val targetBuilding = readln().toInt()
            println(decideCost(Array(numOfBuilding + 1) { false }, targetBuilding, childs, buildingArr))
        }


    }
}
