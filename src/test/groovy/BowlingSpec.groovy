import spock.lang.Specification
import spock.lang.Unroll

class BowlingSpec extends Specification {

    def "nothing"() {
        expect:
            new Bowling().score() == 0
    }

    @Unroll
    "roll #name"() {
        given:
            def bowling = new Bowling()
        when:
            rollList(bowling, input)
        then:
            bowling.score() == result
        where:
            name              | result | input
            "once"            | 1      | [1]
            "twice"           | 3      | [1, 2]
            "a spare"         | 20     | [6, 4, 5]
            "a spare & some"  | 21     | [3, 7, 5, 1]
            "a strike & some" | 28     | [10, 4, 5]
    }

    def rollList(Bowling bowling, List<Integer> list) {
        list.forEach({ bowling.roll it })
    }
}
