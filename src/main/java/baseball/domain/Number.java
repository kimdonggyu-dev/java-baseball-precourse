package baseball.domain;

import baseball.domain.exceptions.NotValidRangeNumberException;

import java.util.Objects;

public class Number {

    private static final String NUMBER_CHECK_REGEX = "-?\\d+(\\.\\d+)?";

    private final Integer number;

    private Number(final String numberText) {
        checkNumeric(numberText);
        checkRangeNumber(numberText);
        this.number = Integer.parseInt(numberText);
    }

    public static Number of(final String numberText) {
        return new Number(numberText);
    }

    private void checkRangeNumber(final String numberText) {
        int number = Integer.parseInt(numberText);
        if (number <= 0 || number >= 10) {
            throw new NotValidRangeNumberException();
        }
    }

    private void checkNumeric(final String numbersText) {
        if (!numbersText.matches(NUMBER_CHECK_REGEX)) {
            throw new NumberFormatException();
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
