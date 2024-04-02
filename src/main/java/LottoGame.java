import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int calculateLottoAmount() {
        return cost.calculateLottoAmount();
    }

    // TODO: Test 코드 작성
    public List<LottoRank> calculateResult(List<Integer> winnerNumbers, int bonusNumber) {
        List<LottoRank> lottoRanks = new ArrayList<>();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.getMatchCount(winnerNumbers);
            boolean matchBonus = lotto.contains(bonusNumber);
            lottoRanks.add(LottoRank.of(matchCount, matchBonus));
        }
        return lottoRanks;
    }

    public Map<LottoRank, Integer> calculateStatistics(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            statistics.put(lottoRank, statistics.getOrDefault(lottoRank, 0) + 1);
        }
        return statistics;
    }

    public Lottos getLottos() {
        return lottos;
    }
}
