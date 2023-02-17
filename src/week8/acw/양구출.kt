data class Island(
    var type: Int,
    var num: Int,
    var next: Int
)

lateinit var graph: Array<Island>
lateinit var safe:BooleanArray

fun main() {
    val N = readln().toInt()
    graph = Array(N + 1) { Island(0, 0, 0) }
    safe = BooleanArray(N + 1) { false }

    //1번섬까지 도달할 경우 살아남음

    for (i in 2..N) {
        val (t, n, w) = readln().split(" ")
        val type = if (t == "S") 0 else 1
        val num = n.toInt()
        val next = w.toInt()
        graph[i].apply {
            this.type = type
            this.num = num
            this.next = next
        }
    }//입력

    var answer = 0L
    for (i in 2..N) {
        if (graph[i].type == 0 && !safe[i]) {
            val survive = findNext(graph[i].next, graph[i].num.toLong(), mutableListOf(i))
            if (survive > 0) {
                answer += survive
            }
        }
    }

    println(answer)
}

fun findNext(islandNum: Int, numOfSheep: Long,history:MutableList<Int>): Long {
    val islandNow=graph[islandNum]

    if(safe[islandNum]){
        for(i in history){
            safe[i]=true
        }
        return numOfSheep
    }//이전에 이미 탐색했던 안전한 길이라면 바로 return
    if(islandNum==1){
        for(i in history){
            safe[i]=true
        }
        return numOfSheep
    }//1번 섬에 도착할 경우 return

    if(islandNow.type==0){
        //양일 경우 현재 양 수를 더하여 계속 진행.
        return findNext(islandNow.next,numOfSheep+islandNow.num,history.apply { add(islandNum) })
    }else{
        //늑대일 경우 늑대 수 빼주기
        val survive=numOfSheep-islandNow.num
        if(survive<=0L){
            //살아남은 양이 없다면 바로 종료
            for(i in history){
                graph[i].next=islandNum
                graph[i].num=0
            }
            islandNow.num-=numOfSheep.toInt()
            return 0
        }else{
            //살아 남은 양이 있다면 계속 진행
            islandNow.num=0
            return findNext(islandNow.next,survive,history.apply { add(islandNum) })
        }
    }

}