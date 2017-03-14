class Frame {
    private val rolls: MutableList<Int> = mutableListOf()
    val isNew: Boolean
        get() = rolls.size == 0
    val isOpen: Boolean
        get() = rolls.size >= 1 && result < 10
    val isClosed: Boolean
        get() = (isSpare && rolls.size == 3)  || (isOpen && rolls.size == 2)
    val isSpare: Boolean
        get() = rolls.size >= 2 && rolls[0]+rolls[1] == 10
    val result: Int
        get() = rolls.sum()

    fun addRoll(roll: Int) {
        if (roll < 0 || roll > 10) {
            throw InvalidRollException("roll is out of bounds")
        }
        if (isClosed) {
            throw InvalidRollException("frame is closed")
        }
        rolls.add(roll)
    }
}