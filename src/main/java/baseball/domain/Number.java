package baseball.domain;

import baseball.domain.exceptions.NotValidRangeNumberException;

import java.util.Objects;

public class Number {

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

    public void checkNumeric(final String numberText) {
        try {
            Integer.parseInt(numberText);
        } catch (NumberFormatException nfe) {
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
