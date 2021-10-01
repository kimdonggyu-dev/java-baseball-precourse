package baseball.domain;

import baseball.domain.numbers.Numbers;
import baseball.domain.scores.Scores;
import baseball.domain.strategys.NumbersGenerationStrategy;

public class BaseBallGame {

    private final NumbersGenerationStrategy rule;
    private Numbers numbers;


    public BaseBallGame(final NumbersGenerationStrategy rule) {
        this.rule = rule;
        this.numbers = rule.generate();
    }

    public Scores match(final String numbersText) {
        return numbers.match(Numbers.of(numbersText));
    }

    public void clear() {
        this.numbers = rule.generate();
    }
}
