package controller;

import java.util.List;
import model.LottoGame;
import model.LottoRank;
import model.WinnerLottoNumber;
import util.RandomNumberGenerator;
import view.LottoGameView;

public class LottoGameController {

    private final LottoGameView lottoGameView;

    public LottoGameController(LottoGameView lottoGameView) {
        this.lottoGameView = lottoGameView;
    }

    public void playGame() {
        LottoGame lottoGame = new LottoGame(
            lottoGameView.requestCost(),
            lottoGameView.requestManualLottoAmount(),
            lottoGameView.requestManualLottoNumbers(lottoGameView.requestManualLottoAmount()),
            new RandomNumberGenerator()
        );

        lottoGameView.displayLottoAmount(lottoGame.calculateLottoAmount());
        lottoGameView.displayLottos(lottoGame.getLottos());

        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(
            lottoGameView.requestWinningNumbers(),
            lottoGameView.requestBonusNumber()
        );

        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        lottoGameView.displayStatistics(lottoGame.calculateStatistics(lottoRanks));
        lottoGameView.displayProfit(lottoGame.calculateProfit(lottoRanks));
    }
}
