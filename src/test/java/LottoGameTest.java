import java.util.List;
import java.util.Map;
import model.LottoGame;
import model.LottoRank;
import model.WinnerLottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private LottoGame lottoGame;
    private TestNumberGenerator testNumberGenerator;

    @BeforeEach
    void setUp() {
        testNumberGenerator = new TestNumberGenerator(List.of(
            List.of(1, 2, 3, 4, 5, 6)
        ));
        lottoGame = new LottoGame(1000, testNumberGenerator);
    }

    @Test
    void createLottoGameWithValidCost() {
        Assertions.assertNotNull(lottoGame);
    }

    @Test
    void calculateLottoAmountWithValidCost() {
        Assertions.assertEquals(1, lottoGame.calculateLottoAmount());
    }

    @Test
    void calculateResultWithMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        Assertions.assertEquals(1,
            lottoRanks.stream().filter(rank -> rank == LottoRank.FIRST).count());
    }

    @Test
    void calculateResultWithNoMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(7, 8, 9, 10, 11, 12),
            13);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        Assertions.assertEquals(1,
            lottoRanks.stream().filter(rank -> rank == LottoRank.NONE).count());
    }

    @Test
    void calculateStatisticsWithMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        Map<LottoRank, Integer> statistics = lottoGame.calculateStatistics(lottoRanks);
        Assertions.assertEquals(1, statistics.get(LottoRank.FIRST));
    }

    @Test
    void calculateStatisticsWithNoMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(7, 8, 9, 10, 11, 12),
            13);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        Map<LottoRank, Integer> statistics = lottoGame.calculateStatistics(lottoRanks);
        Assertions.assertEquals(1, statistics.get(LottoRank.NONE));
    }

    @Test
    void calculateProfitWithMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        double profit = lottoGame.calculateProfit(lottoRanks);
        Assertions.assertEquals(2000000, profit);
    }

    @Test
    void calculateProfitWithNoMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(7, 8, 9, 10, 11, 12),
            13);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        double profit = lottoGame.calculateProfit(lottoRanks);
        Assertions.assertEquals(0, profit);
    }
}