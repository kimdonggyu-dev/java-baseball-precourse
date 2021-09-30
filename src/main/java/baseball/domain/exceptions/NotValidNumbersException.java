package baseball.domain.exceptions;

public class NotValidNumbersException extends RuntimeException {

    public NotValidNumbersException() {
        super("숫자는 서로다른 3개의 숫자만 가능합니다.");
    }
}
