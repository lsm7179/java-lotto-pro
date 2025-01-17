package lotto.model;

import lotto.util.ConstantString;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;

public class Result {
    private Map<Rank, Integer> matchResult;

    public Result(Lottos lottos, WinningLotto winLotto) {
        this.matchResult = Rank.createRanks();
        for (Lotto lotto : lottos.getLottoGroup()) {
            Rank rank = winLotto.matchRank(lotto);
            matchResultPut(rank);
        }
    }

    public Map<Rank, Integer> getMatchResult() {
        return Collections.unmodifiableMap(matchResult);
    }

    private void matchResultPut(Rank rank) {
        if (rank != null && rank != Rank.MISS) {
            matchResult.put(rank, matchResult.get(rank) + 1);
        }
    }

    public String yield(int size) {
        BigInteger purchase = Price.totalPurchase(size);
        BigInteger lottoRevenueAmount = makeRevenueAmount();
        double lottoYield = lottoRevenueAmount.doubleValue() / purchase.doubleValue();
        return String.format(ConstantString.SECOND_DECIMAL_PLACE, lottoYield);
    }

    private BigInteger makeRevenueAmount() {
        BigInteger amount = BigInteger.ZERO;
        for (Rank rank : matchResult.keySet()) {
            BigInteger rankAmount = new BigInteger(String.valueOf(rank.getWinningMoney()));
            rankAmount = rankAmount.multiply(new BigInteger(String.valueOf(matchResult.get(rank))));
            amount = amount.add(rankAmount);
        }
        return amount;
    }
}
