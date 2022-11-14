package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    // <Func> Return amount of lotto based on input money
    public int getLottoAmount(String money) {
        int lottoAmount = (int) (Integer.parseInt(money) / 1000);

        return lottoAmount;
    }

    // <Func> Return boolean value of Input money is multiple of thousands
    public boolean validateMultiThousands(String money) {
        return (Integer.parseInt(money) % 1000 == 0);
    }

    // <Func> Issue lotto and return it
    public List<List<Integer>> issueLotto(int cnt) {
        List<List<Integer>> lottoList = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            lottoList.add(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        }

        return lottoList;
    }

    // <Func> Get lotto and return guessed num in normal numbers
    public int getNormalSameNum(List<Integer> lotto) {
        int normalCnt = 0;

        for (int i = 0; i < 6; i++) {
            // if number in ans
            if(numbers.contains(lotto.get(i)) == true) {
                normalCnt += 1;
            }
        }

        return normalCnt;
    }

    // <Func> Get boolean value if player get bonus number right
    public boolean isBonusGetRight(List<Integer> lotto, int bonus) {
        return lotto.contains(bonus);
    }

    // <Func> Calculate yield and return int
    public double getYield(int input, int reward) {
        double yield = (double) reward / input;

        return Math.round(yield * 1000) / 1000.0 * 100;
    }
}
