package baseball;

import baseball.view.ConsoleInputView;

public class Application {

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현

        ConsoleInputView inputView = new ConsoleInputView();
        String number = inputView.enterNumber();
    }
}
