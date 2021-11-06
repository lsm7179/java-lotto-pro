package lotto.util;

import lotto.view.ErrorMessage;

public class InputHandler {
    private static final int LOTTO_MAX_BUY_LENGTH = 6;

    public static int price(String priceText) {
        validRange(priceText.length());
        try {
            int lottoCount = PriceUtil.getCount(Integer.parseInt(priceText));
            return lottoCount;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.NUMBER_FORMAT_ERROR);
        }
    }

    private static void validRange(int length) {
        if (length > LOTTO_MAX_BUY_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_MAX_BUY_ERROR);
        }
    }

    public static int[] splitTextToInts(String numbersText) {
        String[] splitedNumbers = numbersText.split(ConstantString.SEPARATOR);
         return mapToInts(splitedNumbers);
    }

    private static int[] mapToInts(String[] splitedNumbers) {
        int[] result = new int[splitedNumbers.length];
        for (int i = 0; i < splitedNumbers.length; i++) {
            result[i] = Integer.parseInt(splitedNumbers[i]);
        }
        return result;
    }
}
