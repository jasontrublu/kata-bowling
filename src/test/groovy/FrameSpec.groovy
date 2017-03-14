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
            frame.isOpen()
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
            frame.getResult() == 3
    }

    @Unroll
    def "exception when too many rolls, #name"() {
        when:
            rollList(frame, input)
        then:
            def e = thrown(InvalidRollException)
            e.message == "frame is closed"
            frame.isClosed()
        where:
            name           | input
            "3 for open "  | [1, 2, 1]
            "4 for spare " | [4, 6, 5, 5]
    }

    @Unroll
    def "frame of #one&#two is spare"() {
        when:
            frame.with {
                addRoll(one)
                addRoll(two)
            }
        then:
            frame.isSpare()
            frame.getResult() == 10
        where:
            one | two
            4   | 6
            1   | 9
            3   | 7
            6   | 4
    }

    def "spare frame is not open"() {
        when:
            frame.with {
                addRoll(4)
                addRoll(6)
            }
        then:
            !frame.isOpen()
    }

    def "extra roll after spare"() {
        when:
            rollList(frame, [4, 6, 5])
        then:
            frame.isSpare()
            frame.getResult() == 15
    }

    def "spare frame with three rolls is closed"() {
        when:
            rollList(frame, [4, 6, 5])
        then:
            frame.isClosed()
    }

    @Unroll
    def "Input is invalid (#name)"() {
        when:
            frame.addRoll(input)
        then:
            def e = thrown(InvalidRollException)
            e.message == "roll is out of bounds"
        where:
            name | input
            "-1" | -1
            "-3" | -3
            "11" | 11
            "12" | 12
    }

    def rollList(Frame frame, List<Integer> list) {
        list.forEach({ frame.addRoll it })
    }
}
