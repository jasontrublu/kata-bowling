class Bowling {
    private val frames: MutableList<Frame> = mutableListOf()
    private var activeFrames: MutableList<Frame> = mutableListOf()
    private var currentFrame: Frame = Frame()

    fun score(): Int {
        return frames.sumBy { it.result }
    }

    fun roll(value: Int): Unit {
        activeFrames.forEach { it.addRoll(value) }
        activeFrames.removeAll { it.isClosed }
        if (currentFrame.isNew) {
            frames.add(currentFrame)
            currentFrame.addRoll(value)
            if (currentFrame.isStrike) {
                activeFrames.add(currentFrame)
                currentFrame = Frame()
            }
        } else {
            currentFrame.addRoll(value)
            if (currentFrame.isSpare) {
                activeFrames.add(currentFrame)
            }
            currentFrame = Frame()
        }
    }
}