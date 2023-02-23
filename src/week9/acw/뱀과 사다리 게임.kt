val arr = IntArray(101) {it}
val visit=BooleanArray(101){false}
val q=ArrayList<Pair<Int,Int>>()
fun main() {
    val (ladderNum, snakeNum) = readln().split(" ").map { it.toInt() }

    for(i in 0 until ladderNum+snakeNum){
        val(s,e)= readln().split(" ").map { it.toInt() }
        if(e>100){
            continue
        }
        arr[s]=e
    }
    var answer=1000

    q.add(Pair(1,0))
    visit[1]=true

    while(q.isNotEmpty()){
        val now=q.removeFirst()
        if(now.first==100){
            answer=now.second
            break
        }

        for(i in 1..6){
            var next=now.first+i
            if(next>100){
                break
            }
            if(arr[next]!=next){
                next=arr[next]
            }
            if(visit[next]){
                continue
            }
            visit[next]=true
            q.add(Pair(next,now.second+1))
        }
    }

    println(answer)


}