package baseball.domain.scores;

import baseball.domain.scores.exception.InvalidScoresSizeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void 스코어_사이즈가_4개_이상이면_오류() {
        assertThrows(InvalidScoresSizeException.class, () -> {
            new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE, Score.STRIKE));
        });
    }

    @Test
    void 스코어_사이즈가_0개_이하면_오류() {
        assertThrows(InvalidScoresSizeException.class, () -> {
            new Scores(new ArrayList<>());
        });
    }

    @Test
    void 스트라이크_개수_검증() {
        Scores scores = new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.BALL));
        assertEquals(scores.strike(), 2);
    }

    @Test
    void 볼_개수_검증() {
        Scores scores = new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.BALL));
        assertEquals(scores.ball(), 1);
    }

    @Test
    void 스트라이크_세개면_게임종료() {
        assertTrue(new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE)).isFinish());
        assertFalse(new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE)).isFinish());
        assertFalse(new Scores(Arrays.asList(Score.STRIKE, Score.BALL)).isFinish());
        assertFalse(new Scores(Arrays.asList(Score.BALL)).isFinish());
    }
}