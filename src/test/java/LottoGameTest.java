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

    @BeforeEach
    void setUp() {
        // 자동 로또 번호
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(List.of(
            List.of(7, 8, 9, 10, 11, 12) // 자동 로또 번호
        ));
        lottoGame = new LottoGame(
            2000,
            1,
            List.of(List.of(1, 2, 3, 4, 5, 6)),
            testNumberGenerator
        );
    }

    @Test
    void createLottoGameWithValidCost() {
        Assertions.assertNotNull(lottoGame);
    }

    @Test
    void calculateLottoAmountWithValidCost() {
        Assertions.assertEquals(2, lottoGame.calculateLottoAmount());
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
        Assertions.assertEquals(1000000, profit);
    }

    @Test
    void calculateProfitWithNoMatchingNumbers() {
        WinnerLottoNumber winnerLottoNumber = WinnerLottoNumber.of(List.of(13, 14, 15, 16, 17, 18),
            19);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winnerLottoNumber);
        double profit = lottoGame.calculateProfit(lottoRanks);
        Assertions.assertEquals(0, profit);
    }
}