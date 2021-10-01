package baseball.domain.matchs;

import java.util.Objects;

public class MatchResult {

    private final int firstPosition;
    private final int secondPosition;

    public MatchResult(final int firstPosition, final int secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public MatchState result() {
        if (firstPosition == -1 || secondPosition == -1) {
            return MatchState.MISS;
        }
        if (firstPosition == secondPosition) {
            return MatchState.NUMBER_POSITION;
        }
        return MatchState.NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchResult that = (MatchResult) o;
        return firstPosition == that.firstPosition && secondPosition == that.secondPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPosition, secondPosition);
    }
}
