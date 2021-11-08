package lotto.model;

import lotto.factory.LottoCreateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private Lotto winLotto;
    private Lottos lottos;
    private Result result;

    @BeforeEach
    void setUp() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(4, 5, 6, 7, 8, 9));
        winLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottos = new Lottos(new Lotto[]{lotto1, lotto2});
        result = new Result(lottos, winLotto);
    }

    @DisplayName("갯수를 입력받아 로또를 여러개 생성하는 기능 검증")
    @Test
    void createLottos() {
        Lottos lottos = LottoCreateFactory.createLottos(20);
        assertThat(lottos.size()).isEqualTo(20);
    }

    @DisplayName("당첨 통계 결과 검증")
    @Test
    void report() {
        assertThat(result.toString()).isEqualTo("3개 일치 (5000원)- 1개\n" +
                "4개 일치 (50000원)- 0개\n" +
                "5개 일치 (1500000원)- 0개\n" +
                "6개 일치 (2000000000원)- 1개\n");
    }

    @DisplayName("수익률 결과")
    @Test
    void name() {
        BigInteger purchase = Price.totalPurchase(lottos.size());

        assertThat(lottos.size()).isEqualTo(2);
        assertThat(purchase.toString()).isEqualTo("2000");
        assertThat(result.makeRevenueAmount()).isEqualTo("2000005000");
        assertThat(result.yield(purchase)).isEqualTo("1000002.50");
    }
}
