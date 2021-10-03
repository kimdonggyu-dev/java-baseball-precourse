package baseball.domain.scores;


import baseball.domain.scores.exception.InvalidScoresSizeException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scores {

    private final List<Score> scores;

    public Scores(final List<Score> scores) {
        checkScoreSize(scores);
        this.scores = scores;
        Collections.sort(this.scores);
    }

    private void checkScoreSize(List<Score> scores) {
        if (scores.size() >= 4 || scores.size() <= 0) {
            throw new InvalidScoresSizeException();
        }
    }

    public int strike() {
        return Collections.frequency(scores, Score.STRIKE);
    }

    public int ball() {
        return Collections.frequency(scores, Score.BALL);
    }

    public boolean isFinish() {
        if (strike() == 3) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores1 = (Scores) o;
        return Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}
