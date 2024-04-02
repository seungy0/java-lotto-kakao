import java.util.List;

public class LottoGameController {

    private final LottoGameView lottoGameView;

    public LottoGameController(LottoGameView lottoGameView) {
        this.lottoGameView = lottoGameView;
    }

    public void playGame() {
        LottoGame lottoGame = new LottoGame(lottoGameView.requestCost());

        lottoGame.makeLottos();
        lottoGameView.displayLottoAmount(lottoGame.calculateLottoAmount());

        Lottos lottos = lottoGame.getLottos();
        lottoGameView.displayLottos(lottos);

        List<Integer> winningNumbers = lottoGameView.requestWinningNumbers();
        int bonusNumber = lottoGameView.requestBonusNumber();

        List<LottoRank> lottoRanks = lottoGame.calculateResult(winningNumbers, bonusNumber);
        lottoGameView.displayStatistics(lottoGame.calculateStatistics(lottoRanks));
    }
}
