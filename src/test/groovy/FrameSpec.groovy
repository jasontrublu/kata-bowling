import spock.lang.Specification
import spock.lang.Unroll

class FrameSpec extends Specification {
    def frame

    void setup() {
        frame = new Frame()
    }

    def "Frame is new"() {
        expect:
            frame.isNew()
    }

    def "Frame is open"() {
        when:
            frame.addRoll(1)
        then:
            !frame.isNew()
            frame.isOpen()
    }

    def "Frame is closed"() {
        when:
            frame.addRoll(1)
            frame.addRoll(2)
        then:
            !frame.isNew()
            !frame.isOpen()
            frame.isClosed()
    }

    def "frame of 1&2 has result 3"() {
        when:
            frame.with {
                addRoll(1)
                addRoll(2)
            }
        then:
            frame.isClosed()
    }

    @Unroll
    def "Input is invalid (#name)"() {
        when:
            frame.addRoll(input)
        then:
            thrown InvalidRollException
        where:
            name | input
            "-1" | -1
            "-3" | -3
            "11" | 11
            "12" | 12
    }
}
