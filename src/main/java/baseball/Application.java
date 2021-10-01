package baseball;

import baseball.domain.BaseBallGame;
import baseball.domain.scores.Scores;
import baseball.domain.strategys.RandomNumbersGenerationStrategy;
import baseball.view.ConsoleInputView;
import baseball.view.ConsoleOutputView;

public class Application {

    public static void main(String[] args) {

        ConsoleInputView inputView = new ConsoleInputView();
        ConsoleOutputView outputView = new ConsoleOutputView();

        BaseBallGame baseBallGame = new BaseBallGame(new RandomNumbersGenerationStrategy());

        while (true) {
            String numbersText = inputView.enterNumber();
            Scores scores = baseBallGame.match(numbersText);
            outputView.print(scores);

            if (scores.isFinish()) {
                outputView.printFinish();
                if (!inputView.checkNextGame()) {
                    return;
                }
                baseBallGame.clear();
            }
        }
    }
}
