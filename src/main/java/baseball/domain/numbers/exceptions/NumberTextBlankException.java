package baseball.domain.numbers.exceptions;

public class NumberTextBlankException extends BusinessException {

    public NumberTextBlankException() {
        super("숫자 입력값이 비어있을 수 없습니다.");
    }
}
