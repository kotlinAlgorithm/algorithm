package week3.acw

import kotlin.math.abs
import kotlin.math.max

class 이진트리 {

    lateinit var arr:Array<Int>
    var minSum=0

    fun Int.pow(n:Int):Int{
        var ret=this
        for(i in 0 until n){
            ret*=2
        }
        return ret
    }

    fun solution(tSize:Int,nodeNum:Int):Int{
        if(nodeNum*2>=tSize){
            minSum+=arr[nodeNum]
            return arr[nodeNum]
        }else{
            val left=solution(tSize,nodeNum*2)
            val right=solution(tSize,nodeNum*2+1)

            minSum+=arr[nodeNum]+ abs(left-right)
            return arr[nodeNum]+ max(left,right)

        }

    }
    fun main(){
        val k= readln().toInt()
        val treeSize=2.pow(k+1)-1
        arr=Array(treeSize+3){0}

        val input=readln().split(" ").map { it.toInt() }
        for(i in input.indices){
            arr[i+2]=input[i]
        }

        solution(treeSize,1)
        println(minSum)
    }
}