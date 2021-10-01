package baseball.domain;

import baseball.domain.matchs.MatchResult;
import baseball.domain.matchs.MatchState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchResultTest {

    @Test
    void 생성() {
        assertDoesNotThrow(() -> new MatchResult(1, 2).equals(new MatchResult(1, 2)));
    }

    @Test
    void 위치와_숫자_일치() {
        assertEquals(new MatchResult(1, 1).result(), MatchState.NUMBER_POSITION);
    }

    @Test
    void 숫자만_일치() {
        assertEquals(new MatchResult(1, 2).result(), MatchState.NUMBER);
    }

    @Test
    void 미스매치() {
        assertEquals(new MatchResult(-1, 2).result(), MatchState.MISS);
        assertEquals(new MatchResult(2, -1).result(), MatchState.MISS);
        assertEquals(new MatchResult(-1, -1).result(), MatchState.MISS);
    }

}