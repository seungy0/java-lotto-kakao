import java.util.List;
import model.Cost;
import model.Lotto;
import model.LottoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {

    private Lotto lotto;
    private TestNumberGenerator testNumberGenerator;

    @BeforeEach
    void setUp() {
        testNumberGenerator = new TestNumberGenerator(List.of(
            List.of(1, 2, 3, 4, 5, 6)
        ));
        lotto = new Lotto(testNumberGenerator);
    }


    @ParameterizedTest
    @ValueSource(ints = {100, 200, 300})
    void validateLottoInvalidCost(int cost) {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new LottoGame(cost, testNumberGenerator));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 2000})
    void validateLottoCost(int cost) {
        Assertions.assertDoesNotThrow(() -> new LottoGame(cost, testNumberGenerator));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 1600})
    void calculateLottoAmount(int money) {
        Cost cost = new Cost(money);
        Assertions.assertEquals(1, cost.calculateLottoAmount());
    }

    @Test
    void getMatchCountWithMatchingNumbers() {
        Lotto otherLotto = new Lotto(testNumberGenerator);
        Assertions.assertEquals(6, lotto.getMatchCount(otherLotto));
    }

    @Test
    void getMatchCountWithNoMatchingNumbers() {
        TestNumberGenerator otherNumberGenerator = new TestNumberGenerator(List.of(
            List.of(7, 8, 9, 10, 11, 12)
        ));
        Lotto otherLotto = new Lotto(otherNumberGenerator);
        Assertions.assertEquals(0, lotto.getMatchCount(otherLotto));
    }

//    @Test
//    void calculateResult() {
//        LottoGame lottoGame = new LottoGame(1000, testNumberGenerator);
//
//        List<Integer> winnerNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
//        int bonusNumber = 7;
//        lottoGame.calculateResult(winnerNumbers, bonusNumber);
//    }
}
