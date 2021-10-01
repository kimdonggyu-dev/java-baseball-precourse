package baseball.domain;

import baseball.domain.numbers.Number;
import baseball.domain.numbers.exceptions.NotValidRangeNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberTest {

    @Test
    void 생성() {
        assertEquals(Number.of("1"), Number.of("1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "10", "100"})
    void 숫자는_1이상_9이하가_아니면_에러(final String numberText) {
        assertThrows(NotValidRangeNumberException.class, () -> Number.of(numberText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "김동규", "우아한테크캠프"})
    void 숫자가_아니고_문자면_에러(final String numberText) {
        assertThrows(NumberFormatException.class, () -> Number.of(numberText));
    }

}