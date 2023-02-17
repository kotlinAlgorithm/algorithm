val paintings= arrayListOf<Painting>()
lateinit var dpTable:Array<Int>

data class Painting(var height:Long,var cost:Int)
fun main(){
    val (N,S)= readln().split(" ").map{it.toInt()}
    paintings.add(Painting(0,0))
    repeat(N){
        val(h,c)= readln().split(" ").map { it.toInt() }
        paintings.add(Painting(h.toLong(),c))
    }//input
    dpTable=Array(N+1){0}

    paintings.sortBy{it.height}

    for (i in 1 .. N) {
        if (paintings[i].height < S) continue
        //현재 그림이 제한 길이 보다 작을 경우는 지나치기 : 왜? 어차피 구매되지 않을 뿐더러 만약 포함되더라도 다른 그림의 크기를 줄여
        //더 좋지 않은 결과만 얻기때문


        dpTable[i] = dpTable[i - 1]
        while (dpTable[i]< i) {
            //현재 위치의 그림보다 밑의 그림에 대해서 놓았을 때 현재그림이 포함되지 않을 경우에는 break
            //즉 현재 그림보다 크기가 작은 그림 중에 그 그림과 현재 그림이 같이 포함될 수 있는 경우 중 가장 큰 그림
            if ((paintings[i].height - paintings[dpTable[i]].height) < S) break
            dpTable[i]++
        }//dpTable



        dpTable[i]--
        //탈출을 조건을 만족하지 않은 채 했으므로 다시 1을 빼주기
    }

    // i를 세우거나 세우지 않거나 두 가지 경우의 수
    for (i in 1..N) {
        dpTable[i] = paintings[i].cost + dpTable[dpTable[i]]
        /*
        일단 dpTable을 i번째 그림의 가격과, 위에서 구한 현재 그림보다 작은 그림 중 같이 포함될 수 있는 가장 큰 그림의
        가격을 더하여 저장한다.
         */

       dpTable[i] = Math.max(dpTable[i], dpTable[i - 1])
        //만약 현재 그림을 포함하지 않았을 경우에 더 큰 값이라면 포함시키지 않는다.
    }

    println(dpTable[N])

}