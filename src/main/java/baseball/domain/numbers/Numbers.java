package baseball.domain.numbers;

import baseball.domain.matchs.MatchResults;
import baseball.domain.numbers.exceptions.DuplicatedNumberException;
import baseball.domain.numbers.exceptions.NotValidNumberLengthException;
import baseball.domain.scores.Scores;

import java.util.*;

public class Numbers {

    private static final String NUMBER_TEXT_SPLIT_REGEX = "";
    private static final String NUMBER_CHECK_REGEX = "-?\\d+(\\.\\d+)?";

    private final List<Number> numbers;

    private Numbers(final List<Number> numbers) {
        checkDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    public static Numbers of(final String numbersText) {
        checkBlank(numbersText);
        checkNumeric(numbersText);
        checkNumberLength(numbersText);
        List<Number> numbers = generateNumbers(numbersText.split(NUMBER_TEXT_SPLIT_REGEX));
        return new Numbers(numbers);
    }

    public static Numbers of(final List<Number> numbers) {
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

    private static void checkDuplicateNumber(final List<Number> numbers) {
        Set<Number> set = new HashSet<>(numbers);
        if (set.size() != 3) {
            throw new DuplicatedNumberException();
        }
    }

    private static List<Number> generateNumbers(final String[] numberTexts) {
        List<Number> numbers = new ArrayList<>();
        for (String number : numberTexts) {
            numbers.add(Number.of(number));
        }
        return numbers;
    }

    public Scores match(final Numbers matchNumbers) {
        MatchResults matchResults = MatchResults.match(this.numbers, matchNumbers.numbers);
        return matchResults.calculate();
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
