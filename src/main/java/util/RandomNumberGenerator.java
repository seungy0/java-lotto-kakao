package util;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.LottoNumber;

public class RandomNumberGenerator implements NumberGenerator {

    public Set<LottoNumber> generateNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.stream()
            .limit(6)
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
    }
}