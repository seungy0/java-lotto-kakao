import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import model.Lotto;
import model.Lottos;
import org.junit.jupiter.api.Test;

class LottosTest {
    @Test
    public void addManualLottoAddsLottoToLottos() {
        Lottos lottos = new Lottos(0, null);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        lottos.addManualLotto(numbers);

        Lotto expectedLotto = Lotto.of(numbers);
        assertTrue(lottos.getLottos().stream()
            .anyMatch(lotto -> lotto.getNumbers().equals(expectedLotto.getNumbers())));
    }
}