class 오름세 {
    import java.io.BufferedReader
    import java.io.BufferedWriter
    import java.io.InputStreamReader
    import java.io.OutputStreamWriter


    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        while (true) {
            val input: String = br.readLine() ?: break
            if (input.trim().isEmpty()) break
            val n = input.trim().toInt()
            val arr = br.readLine().trim().replace("\\s+".toRegex(), " ").split(" ").map { it.toInt() }
            val ansList = mutableListOf<Int>()
            for (i in arr) {
                find(ansList, i)
            }

            bw.write(ansList.size.toString())
            bw.newLine()
        }
        bw.flush()
        br.close()
    }

    fun find(lis: MutableList<Int>, num: Int) {

        if (lis.isEmpty()) {
            lis.add(num)
            return
        }
        var start = 0
        var end = lis.lastIndex


        var mid: Int
        while (start <= end) {
            if (start == end) {
                if (lis[start] >= num) {
                    lis[start] = num
                    //작은 경우에는 대체하기
                } else if (lis.lastIndex == start) {
                    lis.add(num)
                }
                break
            }
            mid = (start + end) / 2
            if (lis[mid] > num) {
                end = mid
            } else if (lis[mid] == num) {
                return
            } else {
                start = mid + 1
            }
        }


    }
}

