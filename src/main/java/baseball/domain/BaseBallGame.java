package baseball.domain;

import baseball.domain.strategys.NumbersGenerationStrategy;

public class BaseBallGame {

    private final Numbers numbers;

    public BaseBallGame(final NumbersGenerationStrategy rule) {
        this.numbers = rule.generate();
    }

    public Scores match(final String numbersText) {
        return numbers.match(Numbers.of(numbersText));
    }
}
