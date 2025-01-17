package lotto.model;

import lotto.view.ErrorMessage;

import java.util.*;

public class Lotto {
    public static final int SIZE = 6;
    public static final int INCREMENT_BY_ONE = 1;
    public static final int INCREMENT_BY_ZERO = 0;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        valid(numbers);
        lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(LottoNumber.of(number));
        }
    }

    private void valid(List<Integer> numbers) {
        validNumberEmpty(numbers);
        validSizeUnmatched(numbers);
        validDuplicate(numbers);
    }

    private void validSizeUnmatched(List<Integer> numbers) {
        if (numbers.size() > SIZE || numbers.size() < SIZE) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_SIZE_UNMATCHED);
        }
    }

    private void validNumberEmpty(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new NullPointerException(ErrorMessage.LOTTO_NULL);
        }
    }

    private static void validDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_ERROR);
        }
    }

    private boolean compare(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public boolean isBounsNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int matchNumber(Lotto lotto) {
        int result = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            result += matchNumberIncrement(lotto, lottoNumber);
        }
        return result;
    }

    private int matchNumberIncrement(Lotto lotto, LottoNumber lottoNumber) {
        if (lotto.compare(lottoNumber)) {
            return INCREMENT_BY_ONE;
        }
        return INCREMENT_BY_ZERO;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
