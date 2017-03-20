class Frame {
    private val rolls: MutableList<Int> = mutableListOf()
    val isNew: Boolean
        get() = rolls.size == 0
    val isOpen: Boolean
        get() = rolls.size >= 1 && result < 10
    val isClosed: Boolean
        get() = ((isSpare || isStrike) && rolls.size == 3)
                || (isOpen && rolls.size == 2)
    val isSpare: Boolean
        get() = rolls.size >= 2 && rolls[0]+rolls[1] == 10
    val isStrike: Boolean
        get() = rolls.size >= 1 && rolls[0] == 10
    val result: Int
        get() = rolls.sum()

    private fun isIllegalRoll(roll: Int): Boolean {
        return rolls.size == 1 && !isStrike && rolls[0] + roll > 10
    }

    fun addRoll(roll: Int) {
        if (roll < 0 || roll > 10) {
            throw InvalidRollException("roll is out of bounds")
        }
        if (isClosed) {
            throw InvalidRollException("frame is closed")
        }
        if (isIllegalRoll(roll)) {
            throw InvalidRollException("sum of regular rolls ")
        }
        rolls.add(roll)
    }
}