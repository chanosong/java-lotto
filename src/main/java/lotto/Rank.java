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
}
