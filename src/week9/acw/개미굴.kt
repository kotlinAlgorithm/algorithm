val graph= mutableListOf<Node>()
data class Node(var name:String="",var child:MutableList<Node>)
fun main(){
    val n= readln().toInt()
    graph.add(Node("",mutableListOf()))//root node

    repeat(n){
        val input= readln().split(" ")
        val num=input[0].toInt()
        var parentNow=graph[0]

        for(i in 1 ..num){
            val checkNewChild=parentNow.child.find { it.name==input[i] }
            if(checkNewChild!=null){
                parentNow=checkNewChild
                continue
                //이미 가지고 있는 자식일 경우 계속 진행
            }
            else{
                //자식이 없을 경우 새로 추가
                parentNow.child.add(Node(input[i], mutableListOf()))
                parentNow=parentNow.child.last()
            }
        }
    }
    printDfs("",graph[0])




}
fun printDfs(prefix:String,nodeNow:Node){
    nodeNow.child.sortBy{it.name}
    //이름 순으로 정렬
    for(i in nodeNow.child){
        println(prefix+i.name)
        printDfs("$prefix--",i)
    }
}
