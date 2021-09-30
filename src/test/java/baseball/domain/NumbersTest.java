package baseball.domain;

import baseball.domain.exceptions.DuplicatedNumberException;
import baseball.domain.exceptions.NotValidNumberLengthException;
import baseball.domain.exceptions.NotValidRangeNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumbersTest {

    @Test
    void 생성() {
        assertEquals(new Numbers("123"), new Numbers("123"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "김동규", "화이팅"})
    void 숫자가_아니면_에러(final String text) {
        assertThrows(NumberFormatException.class, () -> new Numbers(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "1", "1124", "1234125", "00"})
    void 세자리_숫자가_아니면_에러(final String numbersText) {
        assertThrows(NotValidNumberLengthException.class, () -> new Numbers(numbersText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"111", "112", "122", "443"})
    void 숫자가_중복되면_에러(final String numbersText) {
        assertThrows(DuplicatedNumberException.class, () -> new Numbers(numbersText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"000"})
    void 올바르지_않은_범위의_숫자인경우(final String numbersText) {
        assertThrows(NotValidRangeNumberException.class, () -> new Numbers(numbersText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력값이_공백이거나_널일경우(final String numbersText) {
        assertThrows(IllegalArgumentException.class, () -> new Numbers(numbersText));
    }
}
