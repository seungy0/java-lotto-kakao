package model;

import java.util.List;

public class WinnerLottoNumber {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    private WinnerLottoNumber(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public static WinnerLottoNumber of(List<Integer> numbers, int bonusNumber) {
        return new WinnerLottoNumber(Lotto.of(numbers), bonusNumber);
    }

    public int getMatchCount(Lotto lotto) {
        return this.lotto.getMatchCount(lotto);
    }

    public boolean getMatchBonus(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
