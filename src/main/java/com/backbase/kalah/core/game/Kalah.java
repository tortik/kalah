package com.backbase.kalah.core.game;

import java.util.Objects;

public class Kalah {
    private final KalahType kalahType;
    private final Player owner;
    private int stones;
    private final int index;

    public Kalah(KalahType kalahType, Player owner, int index) {
        this(kalahType, owner, index, 6);
    }

    public Kalah(KalahType kalahType, Player owner, int index, int stones) {
        this.kalahType = kalahType;
        this.owner = owner;
        this.index = index;
        this.stones = stones;
    }

    public Player getOwner() {
        return owner;
    }

    public int getStones() {
        return stones;
    }

    public KalahType getKalahType() {
        return kalahType;
    }

    public int getIndex() {
        return index;
    }

    public int extractAllStones() {
        int ret = stones;
        stones = 0;
        return ret;
    }

    public void incrementStone() {
        stones++;
    }

    public void addStones(int stones) {
        this.stones += stones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kalah kalah = (Kalah) o;
        return stones == kalah.stones &&
                index == kalah.index &&
                kalahType == kalah.kalahType &&
                Objects.equals(owner, kalah.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kalahType, owner, stones, index);
    }
}
