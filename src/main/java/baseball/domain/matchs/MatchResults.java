package baseball.domain.matchs;

import baseball.domain.numbers.Number;
import baseball.domain.scores.Score;
import baseball.domain.scores.Scores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchResults {

    private final List<MatchResult> matchResults;

    private MatchResults(final List<MatchResult> matchResults) {
        this.matchResults = matchResults;
    }

    public static MatchResults match(final List<Number> numbers, final List<Number> matchNumbers) {
        List<MatchResult> matchResults = new ArrayList<>();
        for (Number number : numbers) {

            int numberPosition = numbers.indexOf(number);
            int matchNumberPosition = matchNumbers.indexOf(number);

            MatchResult matchResult = new MatchResult(numberPosition, matchNumberPosition);
            matchResults.add(matchResult);
        }

        return new MatchResults(matchResults);
    }

    public Scores calculate() {

        int strike = strike();
        int ball = ball();

        if (strike == 0 && ball == 0) {
            return new Scores(Collections.singletonList(Score.NOTHING));
        }

        List<Score> results = result(strike, Score.STRIKE);
        results.addAll(result(ball, Score.BALL));

        return new Scores(results);
    }

    private int strike() {
        List<MatchState> states = matchStates();
        return Collections.frequency(states, MatchState.NUMBER_POSITION);
    }

    private int ball() {
        List<MatchState> states = matchStates();
        return Collections.frequency(states, MatchState.NUMBER);
    }

    private List<MatchState> matchStates() {
        List<MatchState> states = new ArrayList<>();
        for (MatchResult matchResult : matchResults) {
            MatchState state = matchResult.result();
            states.add(state);
        }
        return states;
    }

    private List<Score> result(final int count, final Score score) {
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            scores.add(score);
        }
        return scores;
    }
}
