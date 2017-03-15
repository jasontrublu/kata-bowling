class Bowling {
    private val frames: MutableList<Frame> = mutableListOf(Frame())
    private var activeFrames: MutableList<Frame> = mutableListOf()
    private val currentFrame: Frame
        get() = frames.last()

    fun score(): Int {
        return frames.sumBy { it.result }
    }

    fun roll(value: Int): Unit {
        handleStillActiveFrames(value)
        if (currentFrame.isNew) {
            handleFirstRollOfFrame(value)
        } else {
            handleSecondRollOfFrame(value)
        }
    }

    private fun handleStillActiveFrames(value: Int) {
        activeFrames.forEach { it.addRoll(value) }
        activeFrames.removeAll { it.isClosed }
    }

    private fun handleFirstRollOfFrame(value: Int) {
        currentFrame.addRoll(value)
        if (currentFrame.isStrike) {
            activeFrames.add(currentFrame)
            frames.add(Frame())
        }
    }

    private fun handleSecondRollOfFrame(value: Int) {
        currentFrame.addRoll(value)
        if (currentFrame.isSpare) {
            activeFrames.add(currentFrame)
        }
        frames.add(Frame())
    }
}