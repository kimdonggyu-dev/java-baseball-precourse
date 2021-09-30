package baseball.domain;

import baseball.domain.exceptions.DuplicatedNumberException;
import baseball.domain.exceptions.NotValidNumberLengthException;
import baseball.domain.exceptions.NotValidRangeNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumbersTest {

    @Test
    void 생성() {
        String numbersText = "123";
        HashSet<Number> numbers = new HashSet<>(Arrays.asList(Number.of(1), Number.of(2), Number.of(3)));

        assertEquals(Numbers.of("123"), Numbers.of(numbersText));
        assertEquals(Numbers.of(numbers), Numbers.of(numbers));
    }

    @Test
    void 세자리_숫자가_아니거나_중복이면_오류() {
        HashSet<Number> numbers = new HashSet<>(Arrays.asList(Number.of(1)));

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
}
