import java.lang.Integer.max

lateinit var appMemory: IntArray
lateinit var appCost: IntArray
lateinit var dpArr: Array<IntArray>


fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    appMemory = readln().split(" ").map { it.toInt() }.toIntArray()
    appCost = readln().split(" ").map { it.toInt() }.toIntArray()

    val maxCost = appCost.sum()
    dpArr = Array(n + 1) { IntArray(maxCost + 1) { 0 } }


    for (i in 1..n) {
        val appMemoryNow = appMemory[i - 1]
        val appCostNow = appCost[i - 1]

        for (j in 0..maxCost) {
            if (j - appCostNow >= 0 && dpArr[i - 1][j - appCostNow] + appMemoryNow > dpArr[i][j]) {
                dpArr[i][j] = dpArr[i - 1][j - appCostNow] + appMemoryNow
            }//i-1번째까지의 프로그램에 대해서만 진행된 dpArr에 대해서 비교를 한다.
            //i번째 프로그램을 포함시켰을 경우에 얻을 수 있는 메모리가 더 크다면 포함시키고, update한다.

            dpArr[i][j] = max(dpArr[i - 1][j], dpArr[i][j])
            //포함시키지 않았을 경우에 더 클 수 있으므로 이 것도 비교해준다.
        }
    }


    for (i in 0..maxCost) {
        if (dpArr[n][i] >= m) {
            println(i)
            break
        }
    }


}