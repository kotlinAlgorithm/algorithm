val direction = mapOf<String, Pair<Int, Int>>(
    "D" to Pair(1, 0),
    "L" to Pair(0, -1),
    "R" to Pair(0, 1),
    "U" to Pair(-1, 0)
)
lateinit var m: Array<Array<String>>
lateinit var visit: Array<IntArray>
var answer = 0
var visitNum=0
fun main() {
    val (h, w) = readln().split(" ").map { it.toInt() }
    m = Array(h) { readln().chunked(1).toTypedArray() }
    visit = Array(h) { IntArray(w) { 0 } }
    for (i in 0 until h) {
        for (j in 0 until w) {
            if (visit[i][j] == 0) {
                visitNum++
                dfs(i, j)
            }
        }
    }
    println(answer)
/*
    println(visit.joinToString("\n"){it.joinToString(" ")})
*/
}

fun dfs(y: Int, x: Int) {
    if(visit[y][x]!=0){
        if (visit[y][x] == visitNum) {
            answer++
        }
        return
    }

    val dir = direction[m[y][x]]
    visit[y][x] = visitNum

    dfs(y + dir!!.first, x + dir.second)
}