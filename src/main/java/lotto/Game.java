package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Game {
    List<List<Integer>> lottoList;
    List<Rank> rankList;
    int inputMoney;


    // <Func> Return boolean value of Input money is multiple of thousands
    public boolean validateMultiThousands(String money) {
        return (Integer.parseInt(money) % 1000 == 0);
    }

    // <Func> Return amount of lotto based on input money
    public int getLottoAmount(String money) {
        int lottoAmount = (int) (Integer.parseInt(money) / 1000);

        return lottoAmount;
    }

    // <Func> Get purchase money and validate it, return amount of lotto
    public int inputMoney() {
        String money;

        System.out.println("구매금액을 입력해 주세요");
        money = readLine();
        this.inputMoney = Integer.parseInt(money);

        if (!validateMultiThousands(money)) {
            System.out.println("[ERROR] 로또 구매 금액은 1000원 단위여야 합니다.");
            throw new IllegalArgumentException();
        }
        System.out.println();

        return getLottoAmount(money);
    }

    // <Func> Issue lotto and return it
    public List<List<Integer>> issueLotto(int cnt) {
        List<List<Integer>> lottoList = new ArrayList<>();
        List<Integer> lotto;

        for (int i = 0; i < cnt; i++) {
            lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(lotto);
            lottoList.add(lotto);
        }

        return lottoList;
    }

    // <Func> Show information of lotto
    public void showLotto() {
        System.out.println(lottoList.size() + "개를 구매했습니다.");
        for (int i = 0; i < lottoList.size(); i++) {
            System.out.println(lottoList.get(i));
        }
        System.out.println();
    }

    // <Func> Get Winning number
    public List<Integer> getWinningNum() {
        List<Integer> winning = new ArrayList<>();
        String[] winningString;

        System.out.println("당첨 번호를 입력해주세요.");

        winningString = readLine().split(",");
        for (int i = 0; i < winningString.length; i++) {
            winning.add(Integer.parseInt(winningString[i]));
        }

        System.out.println();

        return winning;
    }

    // <Func> Get Bonus number
    public int getBonusNum() {
        String bonus;

        System.out.println("보너스 번호를 입력해 주세요");
        bonus = readLine();
        System.out.println();
        
        return Integer.parseInt(bonus);
    }

    // <Func> Output Result
    public void showResult(List<Rank> rankList, int input) {
        int reward = 0;

        System.out.println("당첨 통계");
        System.out.println("---");
        for (Rank rank : Rank.values()) {
            System.out.print(rank.getMatch() + "개 일치");
            if (rank.getPrize() == 30000000) {
                System.out.print(", 보너스 볼 일치 ");
            }
            System.out.println("(" + rank.getPrize() + "원) - "
                    + Collections.frequency(rankList, rank) + "개");

            reward += Collections.frequency(rankList, rank) * rank.getPrize();
        }
        System.out.println("총 수익률은 " + getYield(input, reward) + "%입니다.");
    }

    // <Func> Calculate yield and return int
    public double getYield(int input, int reward) {
        double yield = (double) reward / input;

        return Math.round(yield * 1000) / 1000.0 * 100;
    }

    // <Func> Main run function
    public void run() {
        Lotto lotto;
        int lottoCnt, bonus;

        lottoCnt = inputMoney();
        lottoList = issueLotto(lottoCnt);
        showLotto();

        lotto = new Lotto(getWinningNum());
        bonus = getBonusNum();

        rankList = lotto.getRankList(lottoList, bonus);
        showResult(rankList, lottoCnt * 1000);
    }
}
