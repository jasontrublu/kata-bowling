import spock.lang.Specification
import spock.lang.Unroll

class BowlingSpec extends Specification {

    @Unroll
    "roll #name = #score"() {
        given:
            def bowling = new Bowling()
        when:
            rollList(bowling, rolls)
        then:
            bowling.score() == score
        where:
            name                             | score | rolls
            "nothing"                        | 0     | []
            "once"                           | 1     | [1]
            "twice"                          | 3     | [1, 2]
            "a spare"                        | 20    | [6, 4, 5]
            "a spare & some"                 | 21    | [3, 7, 5, 1]
            "a strike & some"                | 28    | [10, 4, 5]
            "two strikes & some"             | 52    | [10, 10, 4, 5]
            "perfect game"                   | 300   | [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]
            "perfect game, ignore 13th roll" | 300   | [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]
            "normal game, 20 rolls"          | 40    | [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
            "normal game, ignore 21th roll"  | 40    | [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
            "full game, three strikes"       | 86    | [2, 2, 10, 2, 2, 1, 9, 10, 2, 2, 10, 2, 2, 2, 2, 2, 2]
    }

    @Unroll
    def "illegal roll #name throws InvalidRollException"() {
        given:
            def bowling = new Bowling()
        when:
            rollList(bowling, rolls)
        then:
            thrown InvalidRollException
        where:
            name             | rolls
            "6+6 in a frame" | [6, 6]
    }

    def rollList(Bowling bowling, List<Integer> list) {
        list.forEach({ bowling.roll it })
    }
}
