class Frame {
    private val rolls: MutableList<Int> = mutableListOf()
    val isNew: Boolean
        get() = rolls.size == 0
    val isOpen: Boolean
        get() = rolls.size == 1
    val isClosed: Boolean
        get() = rolls.size == 2
    val result: Int
        get() = rolls.sum()

    fun addRoll(roll: Int) {
        if (roll < 0 || roll > 10) {
            throw InvalidRollException("")
        }
        rolls.add(roll)
    }
}