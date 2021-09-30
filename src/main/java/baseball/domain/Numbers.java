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

    private Numbers(final Set<Number> numbers) {
        checkDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    public static Numbers of(final String numbersText) {
        checkBlank(numbersText);
        checkNumeric(numbersText);
        checkNumberLength(numbersText);
        Set<Number> numbers = toNumberSet(numbersText.split(NUMBER_TEXT_SPLIT_REGEX));
        return new Numbers(numbers);
    }

    public static Numbers of(final Set<Number> numbers) {
        return new Numbers(numbers);
    }

    private static void checkBlank(final String numbersText) {
        if (numbersText == null || "".equals(numbersText)) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkNumeric(final String numbersText) {
        if (!numbersText.matches(NUMBER_CHECK_REGEX)) {
            throw new NumberFormatException();
        }
    }

    private static void checkNumberLength(final String numbersText) {
        if (numbersText.length() != 3) {
            throw new NotValidNumberLengthException();
        }
    }

    private static void checkDuplicateNumber(final Set<Number> numbers) {
        if (numbers.size() != 3) {
            throw new DuplicatedNumberException();
        }
    }

    private static Set<Number> toNumberSet(final String[] numberTexts) {
        Set<Number> numbers = new HashSet<>();
        for (String number : numberTexts) {
            numbers.add(Number.of(number));
        }
        return numbers;
    }

    public Scores match(Numbers numbers) {
        return null;
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
