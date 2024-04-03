package model;

import java.util.List;
import java.util.Set;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int number;

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자만 가능합니다.");
        }
        this.number = number;
    }

    public static Set<LottoNumber> of(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(java.util.stream.Collectors.toSet());
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof LottoNumber && number == ((LottoNumber) obj).number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
