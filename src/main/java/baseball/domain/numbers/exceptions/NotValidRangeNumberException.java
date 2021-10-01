package baseball.domain.numbers.exceptions;

public class NotValidRangeNumberException extends RuntimeException {

    public NotValidRangeNumberException() {
        super("올바른 숫자 범위가 아닙니다.");
    }
}
