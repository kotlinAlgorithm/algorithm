class `거짓말`{
    lateinit var peopleArr:Array<MutableList<Int>>
    lateinit var partyArr:Array<MutableList<Int>>
    lateinit var visit:Array<Boolean>
    var trueParty= mutableSetOf<Int>()
    fun main(){
        val (N,M)= readln().split(" ").map { it.toInt() }
        val witness = readln().split(" ").map{
            it.toInt() }

        peopleArr=Array(N+1){ mutableListOf() }
        partyArr=Array(M+1){ mutableListOf() }
        visit=Array(N+1){false}

        repeat(M){
            val input=readln().split(" ").map{it.toInt()}
            for(i in 1..input.lastIndex){
                peopleArr[input[i]].add(it+1)
                partyArr[it+1].add(input[i])
            }
        }
        if(witness[0]==0){
            println(M)
            return
        }

        for(i in 1..witness.lastIndex){
            dfs(witness[i])
        }

        println(M-trueParty.size)



    }
    fun dfs(peopleNum:Int){
        if(visit[peopleNum]){
            return
        }
        visit[peopleNum]=true

        for(party in peopleArr[peopleNum]) {
            trueParty.add(party)
            for(person in partyArr[party]){
                dfs(person)
            }
        }
    }

}
