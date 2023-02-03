package week6

/**
 * https://www.acmicpc.net/problem/20055
 */
data class Slot(var durability: Int, var hasRobot: Boolean) {
    fun isPossibleToSetRobot(): Boolean =
        durability > 0 && hasRobot.not()

    fun setRobot() {
        hasRobot = true
        durability -= 1
    }
}

class ConveyBelt(private val slots: Array<Slot>) {
    private val robotDownIndex = slots.size / 2 - 1

    fun rotate() {
        val lastSlot = slots.last()
        for (i in slots.lastIndex downTo 1) {
            slots[i] = slots[i - 1]
            if (i == robotDownIndex) {
                slots[i].hasRobot = false
            }
        }
        slots[0] = lastSlot
    }

    fun moveRobots() {
        for (i in slots.size / 2 - 2 downTo 1) {
            val nextIndex = (i + 1) % slots.size
            if (slots[i].hasRobot && slots[nextIndex].isPossibleToSetRobot()) {
                moveRobotRight(i)
                if (nextIndex == robotDownIndex) {
                    slots[nextIndex].hasRobot = false
                }
            }
        }
    }

    private fun moveRobotRight(from: Int) {
        slots[from].hasRobot = false
        slots[(from + 1) % slots.size].setRobot()
    }

    fun setRobotAt(index: Int) {
        if (slots[index].isPossibleToSetRobot()) {
            slots[index].setRobot()
        }
    }

    fun countNoDurabilitySlots(): Int =
        slots.count { slot -> slot.durability <= 0}
}


fun main() {
    val (n, noDurabilityCount) = readln().trim().split(" ").map(String::toInt)
    val slots: Array<Slot> = readln().trim()
        .split(" ")
        .map { durability ->
            Slot(durability.toInt(), false)
        }.toTypedArray()
    val conveyBelt = ConveyBelt(slots)

    var step = 1
    while (true) {
        conveyBelt.rotate()
        conveyBelt.moveRobots()
        conveyBelt.setRobotAt(0)
        if (conveyBelt.countNoDurabilitySlots() >= noDurabilityCount) {
            print(step)
            return
        }
        step++
    }
}