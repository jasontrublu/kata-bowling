class Bowling {
    private val frames: MutableList<Frame> = mutableListOf()
    private var activeFrames: MutableList<Frame> = mutableListOf()
    private var currentFrame: Frame = Frame()

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
        frames.add(currentFrame)
        currentFrame.addRoll(value)
        if (currentFrame.isStrike) {
            activeFrames.add(currentFrame)
            currentFrame = Frame()
        }
    }

    private fun handleSecondRollOfFrame(value: Int) {
        currentFrame.addRoll(value)
        if (currentFrame.isSpare) {
            activeFrames.add(currentFrame)
        }
        currentFrame = Frame()
    }
}