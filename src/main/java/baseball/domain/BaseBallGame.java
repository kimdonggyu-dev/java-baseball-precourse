package baseball.domain;

import baseball.domain.strategy.NumberGenerationStrategy;

public class BaseBallGame {

    private final NumberGenerationStrategy rule;

    public BaseBallGame(final NumberGenerationStrategy rule) {
        this.rule = rule;
    }

    public Scores run(final String number) {
        return null;
    }
}
