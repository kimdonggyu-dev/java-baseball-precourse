package baseball.domain.numbers.exceptions;

public class NotValidRangeNumberException extends BusinessException {

    public NotValidRangeNumberException() {
        super("올바른 숫자 범위가 아닙니다. (1~9 사이 숫자만 가능)");
    }
}
