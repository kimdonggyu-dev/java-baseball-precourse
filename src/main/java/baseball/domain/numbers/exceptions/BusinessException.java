package baseball.domain.numbers.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }
}
