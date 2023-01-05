package week2

import java.util.LinkedList
import java.util.StringTokenizer

data class Record(val idx: Int, var left: Record?, var right: Record?)

class Solution {
    fun solution(n: Int, k: Int, cmdArray: Array<String>): String {
        val chart = Chart().makeChart(n, k)
        val removedRecordStack = LinkedList<Record>()
        val isRemoved = BooleanArray(n)

        cmdArray.forEach { cmd ->
            val st = StringTokenizer(cmd)
            val op = st.nextToken()

            when(op) {
                "U" -> {
                    chart.moveCursorUp(st.nextToken().toInt())
                }
                "D" -> {
                    chart.moveCursorDown(st.nextToken().toInt())
                }
                "C" -> {
                    removedRecordStack.addLast(chart.removeCurrentRecord())
                }
                "Z" -> {
                    chart.addBack(removedRecordStack.removeLast())
                }
            }
        }

        removedRecordStack.forEach { record ->
            isRemoved[record.idx] = true
        }

        return isRemoved.map { removed ->
            if (removed) "X" else "O"
        }.joinToString("")
    }
}

class Chart {
    private lateinit var cursor: Record

    fun makeChart(chartSize: Int, cursorIndex: Int): Chart {
        var prevRecord = Record(0, null, null)

        repeat(chartSize - 1) { i ->
            val newRecord = Record(i + 1, prevRecord, null)
            prevRecord?.right = newRecord
            prevRecord = newRecord

            if (i + 1 == cursorIndex) cursor = newRecord
        }

        return this
    }

    fun moveCursorUp(step: Int) {
        repeat(step) {
            cursor = cursor?.left ?: return
        }
    }

    fun moveCursorDown(step: Int) {
        repeat(step) {
            cursor = cursor?.right ?: return
        }
    }

    fun removeCurrentRecord(): Record {
        val currentRecord = cursor
        cursor.left?.right = cursor.right
        cursor.right?.left = cursor.left
        if (cursor.right == null) {
            cursor = cursor.left!!
        } else {
            cursor = cursor.right!!
        }

        return currentRecord
    }

    fun addBack(record: Record) {
        record.left?.right = record
        record.right?.left = record
    }
}