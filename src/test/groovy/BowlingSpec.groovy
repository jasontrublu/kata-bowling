import spock.lang.Specification

class BowlingSpec extends Specification {

    def "nothing"() {
        expect:
            new Bowling().score() == 0
    }

    def "roll once"() {
        given:
            def bowling = new Bowling()
        when:
            bowling.roll(1)
        then:
            bowling.score() == 1
    }
}
