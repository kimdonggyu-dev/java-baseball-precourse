package baseball.domain;

import baseball.domain.numbers.Number;
import baseball.domain.numbers.Numbers;
import baseball.domain.numbers.exceptions.DuplicatedNumberException;
import baseball.domain.numbers.exceptions.NotValidNumberLengthException;
import baseball.domain.numbers.exceptions.NotValidRangeNumberException;
import baseball.domain.scores.Score;
import baseball.domain.scores.Scores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumbersTest {

    @Test
    void 생성() {
        String numbersText = "123";
        List<Number> numbers = Arrays.asList(Number.of(1), Number.of(2), Number.of(3));

        assertEquals(Numbers.of("123"), Numbers.of(numbersText));
        assertEquals(Numbers.of(numbers), Numbers.of(numbers));
    }

    @Test
    void 세자리_숫자가_아니거나_중복이면_오류() {
        List<Number> numbers = Collections.singletonList(Number.of(1));

        assertThrows(DuplicatedNumberException.class, () -> Numbers.of(numbers));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "김동규", "화이팅"})
    void 숫자가_아니면_에러(final String text) {
        assertThrows(NumberFormatException.class, () -> Numbers.of(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "1", "1124", "1234125", "00"})
    void 세자리_숫자가_아니면_에러(final String numbersText) {
        assertThrows(NotValidNumberLengthException.class, () -> Numbers.of(numbersText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"111", "112", "122", "443"})
    void 숫자가_중복되면_에러(final String numbersText) {
        assertThrows(DuplicatedNumberException.class, () -> Numbers.of(numbersText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"000"})
    void 올바르지_않은_범위의_숫자인경우(final String numbersText) {
        assertThrows(NotValidRangeNumberException.class, () -> Numbers.of(numbersText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력값이_공백이거나_널일경우(final String numbersText) {
        assertThrows(IllegalArgumentException.class, () -> Numbers.of(numbersText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "456", "789"})
    void 쓰리_스트라이크(final String numbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(numbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:124", "123:423", "123:173"}, delimiter = ':')
    void 투_스트라이크(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.STRIKE, Score.STRIKE)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:132", "123:321", "123:213"}, delimiter = ':')
    void 원_스트라이크_투볼(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.STRIKE, Score.BALL, Score.BALL)), scores);
        assertEquals(new Scores(Arrays.asList(Score.BALL, Score.STRIKE, Score.BALL)), scores);
        assertEquals(new Scores(Arrays.asList(Score.BALL, Score.BALL, Score.STRIKE)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:134", "123:327", "123:913"}, delimiter = ':')
    void 원_스트라이크_원볼(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.STRIKE, Score.BALL)), scores);
        assertEquals(new Scores(Arrays.asList(Score.BALL, Score.STRIKE)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:154", "123:728", "123:473"}, delimiter = ':')
    void 원_스트라이크(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.STRIKE)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:312", "789:978", "123:231"}, delimiter = ':')
    void 쓰리볼(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.BALL, Score.BALL, Score.BALL)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:391", "789:873", "123:271"}, delimiter = ':')
    void 투볼(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.BALL, Score.BALL)), scores);
    }

    @ParameterizedTest
    @CsvSource(value = {"123:981", "789:172", "123:378"}, delimiter = ':')
    void 원볼(final String numbersText, final String matchNumbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of(matchNumbersText);

        Scores scores = numbers.match(matchNumbers);

        assertEquals(new Scores(Arrays.asList(Score.BALL)), scores);
    }

    @ParameterizedTest
    @ValueSource(strings = {"456", "789"})
    void 낫싱(final String numbersText) {
        Numbers numbers = Numbers.of(numbersText);
        Numbers matchNumbers = Numbers.of("123");

        Scores scores = numbers.match(matchNumbers);
        assertEquals(new Scores(Collections.singletonList(Score.NOTHING)), scores);
    }

    // 투 스트라이크 원볼은 존재할 수 없다.
}
