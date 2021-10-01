package baseball.domain.strategys;

import baseball.domain.numbers.Number;
import baseball.domain.numbers.Numbers;
import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class RandomNumbersGenerationStrategy implements NumbersGenerationStrategy {

    @Override
    public Numbers generate() {
        Set<Number> numbers = new LinkedHashSet<>();
        while (numbers.size() != 3) {
            int number = Randoms.pickNumberInRange(1, 9);
            numbers.add(Number.of(number));
        }
        return Numbers.of(new ArrayList<>(numbers));
    }
}
