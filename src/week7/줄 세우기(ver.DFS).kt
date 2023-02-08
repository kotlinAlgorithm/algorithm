package week7

import java.util.StringTokenizer

lateinit var priorStudents: Array<MutableList<Int>>
lateinit var visited: BooleanArray
private val sortedStudents = StringBuilder()

fun main() {
    var st = StringTokenizer(readln())
    val studentCount = st.nextToken().toInt()
    val relationCount = st.nextToken().toInt()
    priorStudents = Array(studentCount + 1) { mutableListOf() }
    visited = BooleanArray(studentCount + 1)

    repeat(relationCount) {
        st = StringTokenizer(readln())
        val priorStudent = st.nextToken().toInt()
        val afterStudent = st.nextToken().toInt()
        priorStudents[afterStudent].add(priorStudent)
    }

    for (student in 1 until priorStudents.size) {
        if (!visited[student]) {
            visitPriorStudent(student)
        }
    }

    print(sortedStudents.toString())
}

private fun visitPriorStudent(student: Int) {
    visited[student] = true

    priorStudents[student].forEach { priorStudent ->
        if (!visited[priorStudent]) {
            visitPriorStudent(priorStudent)
        }
    }

    sortedStudents.append("$student ")
}