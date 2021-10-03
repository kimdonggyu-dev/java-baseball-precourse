package baseball.domain.numbers.exceptions;

public class NotValidNumberLengthException extends BusinessException {

    public NotValidNumberLengthException() {
        super("숫자는 3개만 가능합니다.");
    }
}
