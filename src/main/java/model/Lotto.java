package model;

import java.util.List;
import java.util.Set;
import util.NumberGenerator;

public class Lotto {

    private final Set<LottoNumber> numbers;

    public Lotto(NumberGenerator numberGenerator) {
        numbers = numberGenerator.generateNumbers();
    }

    private Lotto(Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public int getMatchCount(Lotto lotto) {
        return (int) numbers.stream()
            .filter(lotto::contains)
            .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.stream()
            .anyMatch(lottoNumber::equals);
    }

    public static Lotto of(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }

        return new Lotto(LottoNumber.of(numbers));
    }

    @Override
    public String toString() {
        return numbers.stream()
            .sorted()
            .map(LottoNumber::toString)
            .collect(java.util.stream.Collectors.joining(", ", "[", "]"));
    }
}
