package baseball.domain;

import baseball.domain.numbers.Numbers;
import baseball.domain.scores.Scores;
import baseball.domain.strategys.NumbersGenerationStrategy;

public class BaseBallGame {

    private NumbersGenerationStrategy rule;
    private Numbers numbers;
    private boolean isFinish;

    public BaseBallGame(final NumbersGenerationStrategy rule) {
        this.rule = rule;
        this.numbers = rule.generate();
    }

    public Scores match(final String numbersText) {
        Scores scores = numbers.match(Numbers.of(numbersText));
        if (scores.isFinish()) {
            this.isFinish = true;
        }
        return scores;
    }

    public boolean isFinish() {
        return this.isFinish;
    }

    public void clear() {
        this.isFinish = false;
        this.numbers = rule.generate();
    }
}
