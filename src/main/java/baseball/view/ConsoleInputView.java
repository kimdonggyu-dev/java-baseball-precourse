package baseball.view;

import nextstep.utils.Console;

public class ConsoleInputView {

    public String enterNumber() {
        System.out.println("숫자를 입력해주세요 : ");
        return Console.readLine();
    }

    public boolean isContinueNextGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String s = Console.readLine();
        return s.equals("1");
    }
}
