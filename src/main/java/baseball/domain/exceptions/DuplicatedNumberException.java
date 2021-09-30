package baseball.domain.exceptions;

public class DuplicatedNumberException extends RuntimeException {

    public DuplicatedNumberException() {
        super("숫자는 중복될 수 없습니다.");
    }
}
