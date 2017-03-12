class Bowling {
    var score = 0
    var isSpare = false
    var strikeCount = 0
    var isFrame = false
    var frameScore = 0

    fun score(): Int {
        return score
    }

    fun roll(value: Int): Unit {
        score += value
        addValueAfterStrike(value)
        addValueAfterSpare(value)
        handleFrame(value)
    }

    private fun handleFrame(value: Int) {
        if (isFrame) {
            frameScore += value
            markSpareIfFrameIsTen()
            resetFrame()
            return
        }
        startFrame(value)
        markStrikeIfValueIsTen(value)
    }

    private fun resetFrame() {
        isFrame = false
        frameScore = 0
    }

    private fun startFrame(value: Int) {
        isFrame = true
        frameScore = value
    }

    private fun markStrikeIfValueIsTen(value: Int) {
        if (value == 10) {
            strikeCount = 2
            resetFrame()
        }
    }

    private fun markSpareIfFrameIsTen() {
        if (frameScore == 10) {
            isSpare = true
        }
    }

    private fun addValueAfterStrike(value: Int) {
        if (strikeCount > 0) {
            score += value
            strikeCount--
        }
    }

    private fun addValueAfterSpare(value: Int) {
        if (isSpare) {
            score += value
            isSpare = false
        }
    }
}