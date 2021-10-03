package baseball.domain.numbers;

import baseball.domain.numbers.exceptions.NotValidRangeNumberException;

import java.util.Objects;

public class Number {

    private static final String NUMBER_CHECK_REGEX = "-?\\d+(\\.\\d+)?";

    private final Integer number;

    private Number(final Integer number) {
        checkRangeNumber(number);
        this.number = number;
    }

    public static Number of(final String numberText) {
        checkNumeric(numberText);
        return new Number(Integer.parseInt(numberText));
    }

    public static Number of(final Integer number) {
        return new Number(number);
    }

    private static void checkNumeric(final String numbersText) {
        if (!numbersText.matches(NUMBER_CHECK_REGEX)) {
            throw new NumberFormatException();
        }
    }

    private void checkRangeNumber(final int number) {
        if (number <= 0 || number >= 10) {
            throw new NotValidRangeNumberException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return Objects.equals(number, number1.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
