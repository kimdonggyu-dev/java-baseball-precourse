package baseball.domain.scores.exception;

import baseball.domain.numbers.exceptions.BusinessException;

public class InvalidScoresSizeException extends BusinessException {

    public InvalidScoresSizeException() {
        super("스코어 사이즈가 잘못 되었습니다.");
    }
}
