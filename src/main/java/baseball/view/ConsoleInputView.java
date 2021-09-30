package baseball.view;

import nextstep.utils.Console;

public class ConsoleInputView {

    public String enterNumber() {
        System.out.println("숫자를 입력해주세요 : ");
        return Console.readLine();
    }
}
