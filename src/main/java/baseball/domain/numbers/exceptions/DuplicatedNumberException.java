package baseball.domain.numbers.exceptions;

public class DuplicatedNumberException extends BusinessException {

    public DuplicatedNumberException() {
        super("숫자는 중복될 수 없습니다.");
    }
}
