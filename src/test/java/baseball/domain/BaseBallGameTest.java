package baseball.domain;

import baseball.domain.numbers.Numbers;
import baseball.domain.numbers.exceptions.DuplicatedNumberException;
import baseball.domain.numbers.exceptions.NotValidNumberLengthException;
import baseball.domain.numbers.exceptions.NumberTextBlankException;
import baseball.domain.scores.Score;
import baseball.domain.scores.Scores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class BaseBallGameTest {

    @Test
    void 생성() {
        assertDoesNotThrow(() -> new BaseBallGame(() -> Numbers.of("123")));
    }

    @Test
    void 생성_중복된_숫자는_불가능() {
        assertThrows(DuplicatedNumberException.class, () -> new BaseBallGame(() -> Numbers.of("333")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "1234"})
    void 생성_3자리가_아니면_에러(final String numbersText) {
        assertThrows(NotValidNumberLengthException.class, () -> new BaseBallGame(() -> Numbers.of(numbersText)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 생성_비어있는값일경우(final String numbersText) {
        assertThrows(NumberTextBlankException.class, () -> new BaseBallGame(() -> Numbers.of(numbersText)));
    }

    @Test
    void 매치_쓰리_스트라이크_일경우_finish() {
        BaseBallGame baseBallGame = new BaseBallGame(() -> Numbers.of("123"));
        Scores scores = baseBallGame.match("123");
        assertEquals(scores, new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE)));
        assertTrue(baseBallGame.isFinish());
    }

    @Test
    void 매치_투볼() {
        BaseBallGame baseBallGame = new BaseBallGame(() -> Numbers.of("123"));
        Scores scores = baseBallGame.match("314");
        assertEquals(scores, new Scores(Arrays.asList(Score.BALL, Score.BALL)));
        assertFalse(baseBallGame.isFinish());
    }

    @Test
    void 게임_초기화() {
        // given
        AtomicInteger round = new AtomicInteger(1);
        BaseBallGame baseBallGame = new BaseBallGame(() -> {
            if (round.get() == 1) {
                round.getAndIncrement();
                return Numbers.of("123");
            }
            return Numbers.of("456");
        });
        baseBallGame.match("123");

        // when
        baseBallGame.clear();

        // then
        assertFalse(baseBallGame.isFinish());
        Scores scores = baseBallGame.match("456");
        assertEquals(scores, new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE)));
    }
}