package week4

import java.util.StringTokenizer
import java.util.LinkedList

/**
 * https://www.acmicpc.net/problem/1043
 */
fun main() {
    val (peopleSize, partySize) = readln().trim().split(" ").map(String::toInt)
    val partiesOfPerson = Array(peopleSize + 1) { mutableListOf<Int>() }
    val peopleInParty = Array(partySize) { mutableListOf<Int>() }
    val visitedPerson = BooleanArray(peopleSize + 1)
    val visitedParty = BooleanArray(partySize)
    var truthPartyCount = 0
    val q = LinkedList<Int>()

    var st = StringTokenizer(readln().trim())
    repeat(st.nextToken().toInt()) {
        val person = st.nextToken().toInt()
        q.offer(person)
        visitedPerson[person] = true
    }

    repeat(partySize) { partyIndex ->
        st = StringTokenizer(readln().trim())
        repeat(st.nextToken().toInt()) {
            val person = st.nextToken().toInt()
            peopleInParty[partyIndex].add(person)
            partiesOfPerson[person].add(partyIndex)
        }
    }

    while (q.isNotEmpty()) {
        val truthPerson = q.poll()

        for (party in partiesOfPerson[truthPerson]) {
            if (visitedParty[party]) {
                continue
            }
            visitedParty[party] = true
            truthPartyCount++

            for (person in peopleInParty[party]) {
                if (visitedPerson[person]) continue
                q.offer(person)
                visitedPerson[person] = true
            }
        }
    }

    print(partySize - truthPartyCount)
}