fun compare(list:List<Int>, beginning: Array<IntArray>, target: Array<IntArray>):Boolean{
    val tmp=Array(beginning.size){c->IntArray(beginning[0].size){r->beginning[c][r]}}

    for(i in list){
        if(i<10){
            //가로
            for(j in 0 until beginning[0].size){
                tmp[i][j]=1-tmp[i][j]
            }
        }else{
            for(j in 0 until beginning.size){
                tmp[j][i-10]=1-tmp[j][i-10]
            }
        }
    }//뒤집기

    for(i in beginning.indices){
        for(j in beginning[0].indices){
            if(tmp[i][j]!=target[i][j]){
                return false
            }
        }
    }


    return true
}

fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
    val maxCnt=beginning.size+beginning[0].size
    var answer=0

    val list= mutableListOf<Int>()
    for(i in beginning.indices){
        list.add(i)
    }
    for(i in beginning[0].indices){
        list.add(i+10)
    }
    if(compare(listOf(),beginning,target)){
        return 0
    }
    
    loop1@for(cnt in 1..maxCnt){
        val combList= mutableListOf<List<Int>>()
        comb(list, BooleanArray(list.size){false},combList,cnt,0)
        
        for(c in combList){
            val isSame=compare(c,beginning,target)
            if(isSame){
                answer=cnt
                break@loop1
            }
        }
    }

    if(answer==0){
        return -1
    }
    return answer
}


fun comb(list:List<Int>,choose:BooleanArray,combList:MutableList<List<Int>>,cnt:Int,start:Int){
    if(cnt==0){
        combList.add(list.filterIndexed{idx,_->choose[idx]})
        return
    }
    for(i in start until list.size){
        choose[i]=true
        comb(list,choose,combList,cnt-1,i+1)
        choose[i]=false
    }