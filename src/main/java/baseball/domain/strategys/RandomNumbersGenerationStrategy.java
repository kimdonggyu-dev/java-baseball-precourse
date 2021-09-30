package baseball.domain.strategys;

import baseball.domain.Number;
import baseball.domain.Numbers;
import nextstep.utils.Randoms;

import java.util.HashSet;
import java.util.Set;

public class RandomNumbersGenerationStrategy implements NumbersGenerationStrategy {

    @Override
    public Numbers generate() {
        Set<Number> numbers = new HashSet<>();
        while (numbers.size() != 3) {
            int number = Randoms.pickNumberInRange(1, 9);
            numbers.add(Number.of(number));
        }
        return Numbers.of(numbers);
    }
}
