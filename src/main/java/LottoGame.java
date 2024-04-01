public class LottoGame {
    private static final int MINIMUM_PRICE = 1000;

    public LottoGame(int cost) {
        validate(cost);
    }

    private void validate(int cost) {
        if (cost < MINIMUM_PRICE) {
            throw new IllegalArgumentException("로또 금액이 부족합니다.");
        }
    }
}
