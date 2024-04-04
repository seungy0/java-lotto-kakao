package controller;

import java.util.List;
import model.LottoGame;
import model.LottoRank;
import model.Lottos;
import model.WinnerLottoNumber;
import util.RandomNumberGenerator;
import view.LottoGameView;

public class LottoGameController {

    private final LottoGameView lottoGameView;

    public LottoGameController(LottoGameView lottoGameView) {
        this.lottoGameView = lottoGameView;
    }

    public void playGame() {
        LottoGame lottoGame = new LottoGame(lottoGameView.requestCost(),
            new RandomNumberGenerator());

        lottoGameView.displayLottoAmount(lottoGame.calculateLottoAmount());

        Lottos lottos = lottoGame.getLottos();
        lottoGameView.displayLottos(lottos);

        List<Integer> winningNumbers = lottoGameView.requestWinningNumbers();
        int bonusNumber = lottoGameView.requestBonusNumber();

        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(winningNumbers, bonusNumber);

        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        lottoGameView.displayStatistics(lottoGame.calculateStatistics(lottoRanks));
        lottoGameView.displayProfit(lottoGame.calculateProfit(lottoRanks));
    }
}
