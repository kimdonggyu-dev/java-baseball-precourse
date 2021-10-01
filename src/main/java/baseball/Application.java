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
        boolean isFinish;

        do {
            isFinish = playGame(inputView, outputView, baseBallGame);
        } while (!isFinish(isFinish, inputView, outputView, baseBallGame));
    }

    private static boolean playGame(ConsoleInputView inputView, ConsoleOutputView outputView, BaseBallGame baseBallGame) {
        String numbersText = inputView.enterNumber();
        Scores scores = baseBallGame.match(numbersText);
        outputView.print(scores);
        return scores.isFinish();
    }

    private static boolean isFinish(final boolean isFinish, final ConsoleInputView inputView, final ConsoleOutputView outputView, final BaseBallGame baseBallGame) {
        if (!isFinish) {
            return false;
        }
        outputView.printFinish();
        if (inputView.isContinueNextGame()) {
            baseBallGame.clear();
            return false;
        }
        return true;
    }
}
