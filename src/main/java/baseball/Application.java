package baseball;

import baseball.domain.BaseBallGame;
import baseball.domain.numbers.exceptions.BusinessException;
import baseball.domain.scores.Scores;
import baseball.domain.strategys.RandomNumbersGenerationStrategy;
import baseball.view.ConsoleInputView;
import baseball.view.ConsoleOutputView;

public class Application {

    public static void main(String[] args) {

        ConsoleInputView inputView = new ConsoleInputView();
        ConsoleOutputView outputView = new ConsoleOutputView();

        BaseBallGame baseBallGame = new BaseBallGame(new RandomNumbersGenerationStrategy());

        do {
            playGame(inputView, outputView, baseBallGame);
        } while (!isFinish(inputView, outputView, baseBallGame));
    }

    private static void playGame(final ConsoleInputView inputView, final ConsoleOutputView outputView, final BaseBallGame baseBallGame) {
        try {
            String numbersText = inputView.enterNumber();
            Scores scores = baseBallGame.match(numbersText);
            outputView.print(scores);
        } catch (BusinessException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static boolean isFinish(final ConsoleInputView inputView, final ConsoleOutputView outputView, final BaseBallGame baseBallGame) {
        if (!baseBallGame.isFinish()) {
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
