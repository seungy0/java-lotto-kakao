package model;

import java.util.List;
import util.NumberGenerator;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(NumberGenerator numberGenerator) {
        numbers = numberGenerator.generateNumbers();
    }

    public int getMatchCount(List<Integer> winnerNumbers) {
        return (int) numbers.stream()
            .filter(winnerNumbers::contains)
            .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
