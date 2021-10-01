package baseball.domain.scores;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scores {

    private final List<Score> scores;

    public Scores(final List<Score> scores) {
        this.scores = scores;
        this.scores.sort(null);
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

    public List<Score> elements() {
        return Collections.unmodifiableList(scores);
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
