package com.momid

class Balls(val balls: ArrayList<String>, val history: ArrayList<String> = arrayListOf()) : Data<Balls> {
    override fun clone(): Balls {
        return Balls(ArrayList(balls), ArrayList(history))
    }
}

fun Balls.exchange(give: List<String>, receive: List<String>, name: String): Boolean {
    this.balls.addAll(receive)
    give.forEach {
        val removed = this.balls.remove(it)
        if (!removed) {
            return false
        }
    }

    history.add(name)

    return true
}

fun main() {
    val balls = breadth(Balls(arrayListOf("A", "R", "C")), listOf(
        { it.exchange(listOf("A", "C"), listOf("S", "W"), "rule 1") },
        { it.exchange(listOf("D", "S", "H"), listOf("U", "R", "C"), "rule 2") },
        { it.exchange(listOf("C", "S"), listOf("U", "W"), "rule 3") },
        { it.exchange(listOf("O", "R"), listOf("D", "H"), "rule 4") },
        { it.exchange(listOf("C", "R"), listOf("D"), "rule 5") },
        { it.exchange(listOf("R", "C"), listOf("D", "H"), "rule 6") },
        { it.exchange(listOf("D", "U"), listOf("N"), "rule 7") },
        { it.exchange(listOf("C"), listOf("S", "H"), "rule 8") },
        { it.exchange(listOf("A"), listOf("W"), "rule 9") },
        { it.exchange(listOf("W", "R"), listOf("D"), "rule 10") },
        { it.exchange(listOf("A", "W"), listOf("N"), "rule 11") }
    ))

    balls.forEach {
        println(it.history.joinToString(" ") + " " + it.balls.joinToString(" "))
    }
}
