package lotto.view;

import lotto.model.Rank;

import java.util.Map;

public class ResultView {
    private static final String BOUGHT = "개를 구매했습니다.";
    private static final String YIELD_MESSAGE = "총 수익률은 %s입니다.";

    public static void printBought(int count) {
        System.out.println(count + BOUGHT);
    }

    public static void printYield(String yield) {
        System.out.printf(YIELD_MESSAGE, yield);
    }

    public static void printReport(Map<Rank, Integer> matchResult) {
        StringBuilder sb = new StringBuilder("");
        matchResult.forEach((rank, count) -> {
            sb.append(String.format(Message.STATS, rank.getCountOfMatch(), rank.getWinningMoney(), count));
        });
        System.out.print(sb.toString());
    }
}
