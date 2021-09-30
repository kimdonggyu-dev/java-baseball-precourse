package baseball.domain;

import baseball.domain.exceptions.DuplicatedNumberException;
import baseball.domain.exceptions.NotValidNumberLengthException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Numbers {

    private static final String NUMBER_TEXT_SPLIT_REGEX = "";
    private static final String NUMBER_CHECK_REGEX = "-?\\d+(\\.\\d+)?";

    private final Set<Number> numbers;

    public Numbers(final String numbersText) {
        checkBlank(numbersText);
        checkNumeric(numbersText);
        checkNumberLength(numbersText);

        Set<Number> numbers = toNumberSet(numbersText.split(NUMBER_TEXT_SPLIT_REGEX));

        checkDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    private void checkBlank(final String numbersText) {
        if (numbersText == null || "".equals(numbersText)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNumeric(final String numbersText) {
        if (!numbersText.matches(NUMBER_CHECK_REGEX)) {
            throw new NumberFormatException();
        }
    }

    private void checkNumberLength(final String numbersText) {
        if (numbersText.length() != 3) {
            throw new NotValidNumberLengthException();
        }
    }

    private void checkDuplicateNumber(final Set<Number> numbers) {
        if (numbers.size() != 3) {
            throw new DuplicatedNumberException();
        }
    }

    private Set<Number> toNumberSet(final String[] numberTexts) {
        Set<Number> numbers = new HashSet<>();
        for (String number : numberTexts) {
            numbers.add(Number.of(number));
        }
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers1 = (Numbers) o;
        return Objects.equals(numbers, numbers1.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
