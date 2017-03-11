class Bowling {
    var score = 0

    fun score(): Int {
        return score
    }

    fun roll(value: Int): Unit {
        score = value
    }
}