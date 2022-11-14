package lotto;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1400000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private final int match;
    private final int prize;

    Rank(int match, int prize) {
        this.match = match;
        this.prize = prize;
    }

    public int getMatch() {
        return this.match;
    }

    public int getPrize() {
        return this.prize;
    }

    public Rank distinguishSecondAndThird(boolean matchBonus) {
        if (matchBonus == true) {
            return SECOND;
        }
        return THIRD;
    }

    public Rank getRank(int matchNormal, boolean matchBonus) {
        // TODO: Find rank by match normal number and match bonus number
        if (matchNormal == 5) {
            return distinguishSecondAndThird(matchBonus);
        }

        for (Rank rank : Rank.values()) {
            if (rank.getMatch() == matchNormal) {
                return rank;
            }
        }

        return NONE;
    }
}
