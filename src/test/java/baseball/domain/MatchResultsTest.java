package baseball.domain;

import baseball.domain.matchs.MatchResults;
import baseball.domain.numbers.Number;
import baseball.domain.scores.Score;
import baseball.domain.scores.Scores;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MatchResultsTest {

    @Test
    void 생성() {
        List<Number> numbers = Arrays.asList(Number.of(1), Number.of(2), Number.of(3));
        List<Number> matchNumbers = Arrays.asList(Number.of(1), Number.of(2), Number.of(3));

        assertDoesNotThrow(() -> MatchResults.match(numbers, matchNumbers));
    }

    @Test
    void 매치결과_검증() {
        // given
        List<Number> numbers = Arrays.asList(Number.of(1), Number.of(2), Number.of(3));
        List<Number> matchNumbers = Arrays.asList(Number.of(1), Number.of(2), Number.of(3));
        MatchResults matchResults = MatchResults.match(numbers, matchNumbers);

        // when
        Scores scores = matchResults.calculate();

        //then
        assertEquals(scores, new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE)));
    }

}