class `ACM Craft` {
    fun decideCost(
        visit: Array<Boolean>,
        buildingNum: Int,
        childs: Array<MutableList<Int>>,
        buildCost: MutableList<Int>
    ): Int {

        if (childs[buildingNum].size == 0 || visit[buildingNum]) {
            return buildCost[buildingNum - 1]
            //자식이 없는 경우는 자기자신의 cost를 return
        } else {
            var maxChild = -1
            for (child in childs[buildingNum]) {
                //모든 자식들에 대해 cost를 구하여 max값으로 초기화하기
                val c = decideCost(visit, child, childs, buildCost)
                if (c > maxChild) maxChild = c
                visit[child] = true
            }
            buildCost[buildingNum - 1] = buildCost[buildingNum - 1] + maxChild//자기자신의 cost와 자식들 중 max를 더하여 update
            return buildCost[buildingNum - 1]
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
            }//자신이 지어지기 위해 필요로 하는 node들 추가해주기

            val targetBuilding = readln().toInt()
            println(decideCost(Array(numOfBuilding + 1) { false }, targetBuilding, childs, buildingArr))
        }


    }

}
