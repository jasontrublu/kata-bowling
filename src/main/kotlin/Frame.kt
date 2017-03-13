class Frame {
    private val rolls: MutableList<Int> = mutableListOf()
    val isNew: Boolean
        get() = this.rolls.size == 0
    val isOpen: Boolean
        get() = this.rolls.size == 1
    val isClosed: Boolean
        get() = this.rolls.size == 2

    fun addRoll(roll: Int) {
        if (roll < 0 || roll > 10) {
            throw InvalidRollException("")
        }
        rolls.add(roll)
    }
}