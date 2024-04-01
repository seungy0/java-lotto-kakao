public class LottoGame {
    private final Cost cost;

    public LottoGame(int cost) {
        this.cost = new Cost(cost);
    }

    public int calculateLottoAmount() {
        return cost.calculateLottoAmount();
    }
}
