class Bowling {
    var score = 0
    var isSpare = false
    var frame = 0

    fun score(): Int {
        return score
    }

    fun roll(value: Int): Unit {
        score += value
        addValueAfterSpare(value)
        handleFrame(value)
    }

    private fun handleFrame(value: Int) {
        if (frame > 0) {
            frame += value
            markSpareIfFrameIsTen()
            frame = 0
        } else if (frame == 0 && score > 0) {
            frame = score
        }
    }

    private fun markSpareIfFrameIsTen() {
        if (frame == 10) {
            isSpare = true
        }
    }

    private fun addValueAfterSpare(value: Int) {
        if (isSpare) {
            score += value
            isSpare = false
        }
    }
}