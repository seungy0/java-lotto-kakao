public class LottoGame {

    private final Cost cost;
    private Lottos lottos;

    public LottoGame(int cost) {
        this.cost = new Cost(cost);
    }

    public void makeLottos() {
        int amounts = calculateLottoAmount();
        lottos = new Lottos(amounts);
    }

    private int calculateLottoAmount() {
        return cost.calculateLottoAmount();
    }
}
